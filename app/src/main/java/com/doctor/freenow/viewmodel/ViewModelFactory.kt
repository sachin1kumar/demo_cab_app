package com.doctor.freenow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doctor.freenow.api.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleListViewModel::class.java)) {
            return VehicleListViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}