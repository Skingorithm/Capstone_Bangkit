package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstonegas.api.MLApiConfig
import com.example.capstonegas.model.MLResponse
import com.example.capstonegas.model.Output
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

    fun postImage(base64: String){
        _isLoading.value = true
        val service = MLApiConfig.getApiService().postInput(base64)
        service.enqueue(object : Callback<MLResponse> {
            override fun onResponse(call: Call<MLResponse>, response: Response<MLResponse>) {
//                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null && responseBody.success == true){
                        _data.value = responseBody.output
                        _error.value = false
                        _isLoading.value = false
                    }
                }
                else{
                    _isLoading.value = false
                    _error.value = true
                }
            }

            override fun onFailure(call: Call<MLResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
            }
        })
        _error.value = null
    }
}