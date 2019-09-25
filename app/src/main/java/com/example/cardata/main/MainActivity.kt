package com.example.cardata.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cardata.BaseActivity
import com.example.cardata.BaseFragment
import com.example.cardata.R
import com.example.cardata.databinding.ActivityMainBinding
import com.example.cardata.main.list.CarListFragment
import com.example.cardata.main.login.LoginFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: MainActivityViewModel.Factory

    private val model: MainActivityViewModel by viewModelProvider { factory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        app().appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model.apply {
            loginSuccess.observe(this@MainActivity, Observer {
                replaceFragment(CarListFragment())
            })
        }

        replaceFragment(LoginFragment())
    }

    private fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .commit()
    }
}
