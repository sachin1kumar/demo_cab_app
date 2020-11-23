package com.doctor.freenow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PoiList(var coordinate: Coordinate,
var fleetType: String,
var heading: String,
var id: String): Parcelable