package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.api.MLApiConfig
import com.example.capstonegas.model.InsertMLResponse
import com.example.capstonegas.model.ResultData
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class ResultSkincareViewModel(private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean?>()
    val isError: MutableLiveData<Boolean?> = _isError

    fun postHistory(token: String, userName: String, data: ResultData){
        _isLoading.value = true
        //variable that contain now date
        val now = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
        val service = ApiConfig.getApiService().postHistory("Bearer $token", userName, now, data.image, data.acne.roundToInt(), data.wrinkle.roundToInt(), data.bspot.roundToInt(), data.peye.roundToInt(), data.average.roundToInt())
        service.enqueue(object: Callback<InsertMLResponse>{
            override fun onResponse(
                call: Call<InsertMLResponse>,
                response: Response<InsertMLResponse>
            ) {
                if(response.isSuccessful) {
                    if (response.body()?.status == "success") {
                        _isLoading.value = false
                        _isError.value = false
                    } else {
                        _isLoading.value = false
                        _isError.value = true
                    }
                }
                else{
                    _isLoading.value = false
                    _isError.value = true
                }
            }

            override fun onFailure(call: Call<InsertMLResponse>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
            }
        })
        _isError.value = null
    }

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}