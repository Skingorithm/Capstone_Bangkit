package com.example.capstonegas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetHistoryByUsernameResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("datalistset")
	val datalistset: List<DatalistsetHistory>? = null
)

@Parcelize
data class DatalistsetHistory(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("ScanDate")
	val scanDate: String? = null,

	@field:SerializedName("Username")
	val username: String? = null,

	@field:SerializedName("Total")
	val total: Int? = null,

	@field:SerializedName("FaceShape")
	val faceShape: String? = null,

	@field:SerializedName("Photo")
	val photo: String? = null,

	@field:SerializedName("Jerawat")
	val jerawat: Int? = null,

	@field:SerializedName("FlekHitam")
	val flekHitam: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("MataPanda")
	val mataPanda: Int? = null,

	@field:SerializedName("Kerutan")
	val kerutan: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable
