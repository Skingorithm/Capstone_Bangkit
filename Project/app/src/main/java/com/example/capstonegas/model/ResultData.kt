package com.example.capstonegas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultData(
    @field:SerializedName("average")
    val average: Double,

    @field:SerializedName("acne")
    val acne: Double,

    @field:SerializedName("peye")
    val peye: Double,

    @field:SerializedName("wrinkle")
    val wrinkle: Double,

    @field:SerializedName("bspot")
    val bspot: Double,

    @field:SerializedName("image")
    val image: String,
): Parcelable
