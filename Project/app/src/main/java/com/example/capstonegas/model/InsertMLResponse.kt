package com.example.capstonegas.model

import com.google.gson.annotations.SerializedName

data class InsertMLResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
