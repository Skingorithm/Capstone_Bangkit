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
	val createdAt: String,

	@field:SerializedName("ScanDate")
	val scanDate: String,

	@field:SerializedName("Username")
	val username: String,

	@field:SerializedName("Total")
	val total: Int,

	@field:SerializedName("Photo")
	val photo: String,

	@field:SerializedName("Jerawat")
	val jerawat: Int,

	@field:SerializedName("FlekHitam")
	val flekHitam: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("MataPanda")
	val mataPanda: Int,

	@field:SerializedName("Kerutan")
	val kerutan: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String,
) : Parcelable
