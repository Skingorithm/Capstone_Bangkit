package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.Datalistuser
import com.example.capstonegas.model.GetUserByUsernameResponse
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val pref: UserPreference) : ViewModel() {

    private val _dataUser = MutableLiveData<Datalistuser>()
    val dataUser: LiveData<Datalistuser> = _dataUser

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun getDataUser(username: String, token: String) {
        val bearer = "Bearer $token"
        val client = ApiConfig.getApiService().getUserByUsername(bearer, username)
        client.enqueue(object : Callback<GetUserByUsernameResponse> {
            override fun onResponse(
                call: Call<GetUserByUsernameResponse>,
                response: Response<GetUserByUsernameResponse>
            ) {

                if (response.isSuccessful) {
                    _dataUser.value = response.body()?.datalistset as Datalistuser
                }
                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetUserByUsernameResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}