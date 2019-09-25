package com.example.cardata.main.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardata.BaseFragment
import com.example.cardata.R
import com.example.cardata.databinding.FragmentCarListBinding
import com.example.cardata.detail.DetailActivity
import com.example.cardata.main.MainActivityViewModel
import com.mcxiaoke.koi.ext.toast
import javax.inject.Inject

class CarListFragment : BaseFragment() {

    @Inject
    lateinit var factory: MainActivityViewModel.Factory

    private val model: MainActivityViewModel by activityViewModelProvider { factory }

    lateinit var binding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentCarListBinding>(
        inflater,
        R.layout.fragment_car_list,
        container,
        false
    ).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        app().appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        binding.list.apply {
            val dividerDeco = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerDeco)
        }

        model.apply {
            loadCars()
                .doOnSubscribe(disposer::disposeOnDestroy)
                .doOnSubscribe { showLoader() }
                .doFinally { hideLoader() }
                .subscribe(
                    { model.updateCarList(it) },
                    { toast(it.message ?: getString(R.string.load_car_list_error)) }
                )
            carInfoClick.observe(this@CarListFragment, Observer {
                Intent(activity!!, DetailActivity::class.java)
                    .apply { putExtra("imei", it.imei) }
                    .let { startActivity(it) }
            })
        }

        binding.model = model
    }
}
