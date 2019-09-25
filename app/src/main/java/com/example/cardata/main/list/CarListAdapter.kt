package com.example.cardata.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cardata.R
import com.example.cardata.SingleLiveEvent
import com.example.cardata.databinding.ViewCarBinding
import com.example.cardata.main.list.network.CarInfo

class CarListAdapter(private val carInfoClick: SingleLiveEvent<CarInfo>) :
    RecyclerView.Adapter<CarViewHolder>(), ClickListener {

    var carList: List<CarInfo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CarViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_car,
                parent,
                false
            )
        )

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position], this)
    }

    override fun onClick(info: CarInfo) {
        carInfoClick.postValue(info)
    }
}

class CarViewHolder(private val binding: ViewCarBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(info: CarInfo, listener: ClickListener) {
        binding.info = info
        binding.listener = listener
    }
}

interface ClickListener {

    fun onClick(info: CarInfo)
}
