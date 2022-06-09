package com.example.capstonegas.model

import com.google.gson.annotations.SerializedName

data class InsertAlarmResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
