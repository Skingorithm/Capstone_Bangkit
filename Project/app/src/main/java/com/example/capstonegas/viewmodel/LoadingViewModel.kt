package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstonegas.api.MLApiConfig
import com.example.capstonegas.model.InputModel
import com.example.capstonegas.model.MLResponse
import com.example.capstonegas.model.Output
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _data = MutableLiveData<Output?>()
    val data: MutableLiveData<Output?> = _data

    private val _error = MutableLiveData<Boolean?>()
    val error: MutableLiveData<Boolean?> = _error

    private lateinit var inputModel: InputModel

    fun postImage(base64: String){
        _isLoading.value = true
        inputModel = InputModel(base64)
        val service = MLApiConfig.getApiService().postInput(inputModel)
        service.enqueue(object : Callback<MLResponse> {
            override fun onResponse(call: Call<MLResponse>, response: Response<MLResponse>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null && responseBody.success == true){
                        _data.value = responseBody.output
                        _error.value = false
                        _isLoading.value = false
                    }
                }
                else{
                    Log.d("Response","Response Not Successful")
                    _isLoading.value = false
                    _error.value = true
                }
            }

            override fun onFailure(call: Call<MLResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                t.message?.let { Log.d("Ini Error", it) }
            }
        })
        _error.value = null
    }
}