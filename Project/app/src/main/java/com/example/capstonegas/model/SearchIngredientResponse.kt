package com.example.capstonegas.model

import com.google.gson.annotations.SerializedName

data class SearchIngredientResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("datalistset")
	val datalistset: Datalistset? = null
)

data class Datalistset(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("IngredEffect")
	val ingredEffect: String? = null,

	@field:SerializedName("IngredFunction")
	val ingredFunction: String? = null,

	@field:SerializedName("IngredName")
	val ingredName: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
