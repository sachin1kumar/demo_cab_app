package com.doctor.freenow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doctor.freenow.R
import com.doctor.freenow.model.PoiList
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {

    private var mapFragment: SupportMapFragment? = null
    private val vehicleList = ArrayList<PoiList>()
    private var googleMapInstance: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        googleMapInstance = googleMap
        addMarkersToMap()
        /* val sydney = LatLng(-34.0, 151.0)
         googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
         googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    private fun addMarkersToMap() {
        googleMapInstance?.clear()
        if (vehicleList.size > 0) {
            for (i in vehicleList.indices) {
                try {
                    val location = LatLng(vehicleList[i].coordinate.latitude.toDouble(), vehicleList[i].coordinate.longitude.toDouble())
                    googleMapInstance?.addMarker(MarkerOptions()
                            .position(location)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_car)))
                    googleMapInstance?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10.5f))
                } catch (e: Exception) {
                    //e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
    }

    fun setVehiclesInMap(listOfVehicles: List<PoiList>?) {
        listOfVehicles?.let { vehicleList.addAll(it) }
        mapFragment?.getMapAsync(callback)
    }

    fun zoomMap(poiList: PoiList) {
        val location = LatLng(poiList.coordinate.latitude.toDouble(), poiList.coordinate.longitude.toDouble())
        googleMapInstance?.clear()
        googleMapInstance?.addMarker(MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_car)))
        googleMapInstance?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 50f))
    }

    override fun onDestroy() {
        super.onDestroy()
        mapFragment = null
        googleMapInstance = null
        vehicleList.clear()
    }
}