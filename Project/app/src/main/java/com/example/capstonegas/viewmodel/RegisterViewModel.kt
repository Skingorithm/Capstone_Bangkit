package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    fun postUser(name: String, username: String, email: String, password: String) {
        val client = ApiConfig.getApiService().registerUsers(name, username, email, password)
        client.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        if(data.status == "Success"){
                            Log.d(TAG, "Success onResponse: ${data.message}")
                            _registerSuccess.value = true
                        }
                        else{
                            Log.e(TAG, "ERROR CREATING USER: ${data.message}")
                            _registerSuccess.value = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _registerSuccess.value = false
            }
        })
    }

    companion object {
        private const val TAG = "RegisterViewModel"
    }
}