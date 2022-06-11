package com.example.capstonegas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultData(
    @field:SerializedName("average")
    val average: Double? = null,

    @field:SerializedName("acne")
    val acne: Double? = null,

    @field:SerializedName("peye")
    val peye: Double? = null,

    @field:SerializedName("wrinkle")
    val wrinkle: Double? = null,

    @field:SerializedName("bspot")
    val bspot: Double? = null,

    @field:SerializedName("image")
    val image: String? = null,
): Parcelable
