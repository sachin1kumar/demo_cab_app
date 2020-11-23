package com.doctor.freenow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(var latitude: String,
var longitude: String) : Parcelable