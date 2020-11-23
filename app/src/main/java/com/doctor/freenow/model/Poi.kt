package com.doctor.freenow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poi(var poiList: Array<PoiList>): Parcelable