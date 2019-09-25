package com.example.cardata.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cardata.BaseFragment
import com.example.cardata.R
import com.example.cardata.databinding.FragmentLoginBinding
import com.example.cardata.main.MainActivityViewModel
import com.mcxiaoke.koi.ext.toast
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var factory: MainActivityViewModel.Factory

    private val model: MainActivityViewModel by activityViewModelProvider { factory }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentLoginBinding>(
        inflater,
        R.layout.fragment_login,
        container,
        false
    ).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        app().appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        model.apply {
            loginClick.observe(this@LoginFragment, Observer {
                login()
                    .doOnSubscribe(disposer::disposeOnDestroy)
                    .doOnSubscribe { showLoader() }
                    .doFinally { hideLoader() }
                    .subscribe(
                        {
                            proceedToCarList(it.token)
                        },
                        {
                            clearCredentials()
                            toast("Wrong email and/or password")
                        }
                    )
            })
        }

        binding.model = model
    }
}
