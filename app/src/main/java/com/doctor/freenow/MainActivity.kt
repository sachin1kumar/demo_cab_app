package com.doctor.freenow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.doctor.freenow.api.ApiHelperImpl
import com.doctor.freenow.api.RetrofitBuilder
import com.doctor.freenow.utils.Status
import com.doctor.freenow.utils.StringUtils
import com.doctor.freenow.viewmodel.VehicleListViewModel
import com.doctor.freenow.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: VehicleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupAPICall()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(
                        ApiHelperImpl(RetrofitBuilder.apiService))).get(VehicleListViewModel::class.java)
    }

    private fun setupAPICall() {
        viewModel.getVehicles().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.e("Test","Success:${it.data?.poiList.toString()}")
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    Log.e("Test","Success:${it.status}")
                }
            }
        })
        viewModel.fetchVehicles()
    }
}