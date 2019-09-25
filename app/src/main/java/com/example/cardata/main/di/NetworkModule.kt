package com.example.cardata.main.di

import com.example.cardata.BuildConfig
import com.example.cardata.TokenReference
import com.example.cardata.main.list.network.ListApi
import com.example.cardata.main.list.network.ListService
import com.example.cardata.main.login.network.LoginApi
import com.example.cardata.main.login.network.LoginService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideTokenReference() = TokenReference()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @HeaderInterceptor
    @Singleton
    fun provideHeaderInterceptor() = Interceptor { chain ->
        var request = chain.request()

        val headers = request.headers.newBuilder()
            .add("contentType", "application/json; charset=UTF-8")
            .build()

        request = request.newBuilder().headers(headers).build()

        chain.proceed(request)
    }

    @Provides
    @LoggingInterceptor
    @Singleton
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @AuthInterceptor
    @Singleton
    fun provideAuthInterceptor(reference: TokenReference) = Interceptor { chain ->
        var request = chain.request()

        val headers = request.headers.newBuilder()
            .add("Authorization", "Token ${reference.get()}")
            .build()

        request = request.newBuilder().headers(headers).build()

        chain.proceed(request)
    }

    @Provides
    @Singleton
    @SimpleClient
    fun provideOkHttp(
        @HeaderInterceptor headerInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    @AuthClient
    fun provideAuthOkHttp(
        @HeaderInterceptor headerInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @AuthInterceptor authInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    private fun createRetrofit(gson: Gson) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    @SimpleRetrofit
    fun provideSimpleRetrofit(@SimpleClient client: OkHttpClient, gson: Gson): Retrofit =
        createRetrofit(gson)
            .newBuilder()
            .client(client)
            .build()

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(@AuthClient client: OkHttpClient, gson: Gson): Retrofit =
        createRetrofit(gson)
            .newBuilder()
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideLoginApi(@SimpleRetrofit retrofit: Retrofit) =
        LoginApi(retrofit.create(LoginService::class.java))

    @Provides
    @Singleton
    fun provideCarListApi(@AuthRetrofit retrofit: Retrofit) =
        ListApi(retrofit.create(ListService::class.java))
}
