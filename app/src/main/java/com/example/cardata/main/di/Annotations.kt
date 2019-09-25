package com.example.cardata.main.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class HeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class AuthInterceptor

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class SimpleClient

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class AuthClient


@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class SimpleRetrofit

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class AuthRetrofit
