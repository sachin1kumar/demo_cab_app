package com.doctor.freenow.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.doctor.freenow.R
import com.doctor.freenow.api.ApiHelperImpl
import com.doctor.freenow.api.RetrofitBuilder
import com.doctor.freenow.model.PoiList
import com.doctor.freenow.utils.Status
import com.doctor.freenow.utils.StringUtils
import com.doctor.freenow.viewmodel.VehicleListViewModel
import com.doctor.freenow.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), CallBackInterface {

    private lateinit var viewModel: VehicleListViewModel
    private var vehicleListFragment: VehicleListFragment? = VehicleListFragment()
    private var mapFragment: MapsFragment? = MapsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupAPICall()
        setupFragments()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(VehicleListViewModel::class.java)
    }

    private fun setupAPICall() {
        viewModel.getVehicles().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.poiList?.toList()?.let { it1 -> vehicleListFragment?.renderList(it1) }
                    vehicleListFragment?.dismissProgress()
                    mapFragment?.setVehiclesInMap(it.data?.poiList?.toList())
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    //Handle Error
                    vehicleListFragment?.dismissProgress()
                    Toast.makeText(this, StringUtils.API_FAIL_MESG, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.fetchVehicles()
    }

    private fun setupFragments() {
        //launching vehicle fragment
        vehicleListFragment?.setCallBackInterface(this)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.vehicle_container, vehicleListFragment!!)
            .commit()
        //launching map fragment.
        supportFragmentManager
            .beginTransaction()
            .add(R.id.map_container, mapFragment!!)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        vehicleListFragment = null
        mapFragment = null
    }

    override fun callBackMethod(poiList: PoiList) {
        mapFragment?.zoomMap(poiList)
    }
}