package com.example.cardata.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cardata.BaseActivity
import com.example.cardata.R
import com.example.cardata.databinding.ActivityDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mcxiaoke.koi.ext.toast
import javax.inject.Inject

class DetailActivity : BaseActivity(), OnMapReadyCallback {

    @Inject
    lateinit var factory: DetailActivityViewModel.Factory

    private val model: DetailActivityViewModel by viewModelProvider { factory }

    private val icon by lazy { BitmapDescriptorFactory.fromBitmap(convertToBitmap(R.drawable.ic_navigation)) }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        app().detailComponent?.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
            .getMapAsync(this)

        binding.model = model

        lifecycle.addObserver(PollingTracker(
            { model.shouldUpdate.compareAndSet(false, true) },
            { model.shouldUpdate.compareAndSet(true, false) }
        ))
    }

    override fun onMapReady(map: GoogleMap) {
        model.apply {
            pollState(intent.getStringExtra("imei"))
                .doOnSubscribe(disposer::disposeOnDestroy)
                .subscribe(
                    {
                        updateCarState(it)
                        map.updatePosition(it.latitude, it.longitude, it.heading)
                    },
                    { toast(it.message ?: getString(R.string.update_state_error)) }
                )
        }
    }

    override fun onDestroy() {
        app().detailComponent = null
        super.onDestroy()
    }

    private fun GoogleMap.updatePosition(lat: Double, lng: Double, heading: Int) {
        clear()
        val position = LatLng(lat, lng)
        addMarker(
            MarkerOptions()
                .anchor(0.5f, 0.5f)
                .draggable(false)
                .icon(icon)
                .rotation(heading.toFloat())
                .position(position)
        )
        animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16f))
    }
}
