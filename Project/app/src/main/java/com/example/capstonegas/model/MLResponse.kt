package com.example.capstonegas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MLResponse(

	@field:SerializedName("output")
	val output: Output? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Output(

	@field:SerializedName("average")
	val average: Double? = null,

	@field:SerializedName("acne")
	val acne: Double? = null,

	@field:SerializedName("peye")
	val peye: Double? = null,

	@field:SerializedName("wrinkle")
	val wrinkle: Double? = null,

	@field:SerializedName("bspot")
	val bspot: Double? = null
) : Parcelable