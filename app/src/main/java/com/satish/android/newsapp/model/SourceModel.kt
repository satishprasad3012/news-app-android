package com.satish.android.networktest.model

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceModel(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null
) : Parcelable