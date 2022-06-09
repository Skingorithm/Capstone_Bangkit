package com.example.capstonegas.model

data class AllIngredientResponse(
	val error: String? = null,
	val message: String? = null,
	val datalistset: List<DatalistsetItem>? = null
)

data class DatalistsetItem(
	val createdAt: String? = null,
	val ingredEffect: String? = null,
	val ingredFunction: String? = null,
	val ingredName: String? = null,
	val updatedAt: String? = null
)

