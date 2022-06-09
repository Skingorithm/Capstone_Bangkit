package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientAnalyzeViewModel(private val pref: UserPreference) : ViewModel() {

    private val _ingredient = MutableLiveData<Datalistset?>()
    val ingredient : LiveData<Datalistset?> = _ingredient

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _allIngredient = MutableLiveData<List<DatalistsetItem>?>()
    val allIngredient : LiveData<List<DatalistsetItem>?> = _allIngredient

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun getIngredient(token: String, ingredientName: String) {
        val bearer = "Bearer $token"
        _isLoading.value = true
        val client = ApiConfig.getApiService().getIngredient(bearer, ingredientName)
        client.enqueue(object: Callback<SearchIngredientResponse> {
            override fun onResponse(
                call: Call<SearchIngredientResponse>,
                response: Response<SearchIngredientResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _ingredient.value = response.body()?.datalistset as Datalistset
                }

                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchIngredientResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getAllIngredient(token: String) {
        val bearer = "Bearer $token"
        Log.d(TAG,"ini $bearer")
        val client = ApiConfig.getApiService().getAllIngredient(bearer)
        client.enqueue(object: Callback<AllIngredientResponse> {
            override fun onResponse(
                call: Call<AllIngredientResponse>,
                response: Response<AllIngredientResponse>
            ) {
                Log.d(TAG,"ini masuk response")
                if (response.isSuccessful) {
                    _allIngredient.value = response.body()?.datalistset
                    Log.d(TAG,"ini berhasil ")
                    Log.d(TAG, _allIngredient.value.toString())
                }

                else {
                    Log.e(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<AllIngredientResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "IngredientViewModel"
    }
}
