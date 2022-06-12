package com.example.capstonegas.view.loading

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.capstonegas.databinding.ActivityLoadingBinding
import com.example.capstonegas.model.ResultData
import com.example.capstonegas.view.camera.UploadActivity
import com.example.capstonegas.view.resultskincare.ResultSkincareActivity
import com.example.capstonegas.viewmodel.LoadingViewModel
import java.io.File

class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding
    private val viewModel: LoadingViewModel by viewModels()
    private lateinit var base64: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val base64 = intent.getStringExtra("image").toString()
        //function to open base64 file
        openFile()

        viewModel.postImage(base64)

        viewModel.data.observe(this) {
            if (it != null) {
                val intent = Intent(this, ResultSkincareActivity::class.java)
                val result = it.average?.let { it1 -> it.acne?.let { it2 ->
                    it.peye?.let { it3 ->
                        it.wrinkle?.let { it4 ->
                            it.bspot?.let { it5 ->
                                ResultData(it1,
                                    it2, it3, it4, it5, base64)
                            }
                        }
                    }
                } }
                intent.putExtra(ResultSkincareActivity.ML_DATA, result)
                startActivity(intent)
                finish()
            }
        }

        viewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBarLoading.visibility = android.view.View.VISIBLE
            } else {
                binding.progressBarLoading.visibility = android.view.View.GONE
            }
        }

        viewModel.error.observe(this) {
            if (it == true) {
                binding.loadingTitle.visibility = android.view.View.GONE
                binding.failedTitle.visibility = android.view.View.VISIBLE
                binding.btnRephoto.visibility = android.view.View.VISIBLE
            }
            else{
                binding.loadingTitle.visibility = android.view.View.VISIBLE
                binding.failedTitle.visibility = android.view.View.GONE
                binding.btnRephoto.visibility = android.view.View.GONE
            }
        }

        setupAction()
    }

    private fun openFile(){
        val path = applicationContext.filesDir
        val letDirectory = File(path, "Picture Base64")
        val file = File(letDirectory, "fotoku.txt")
        base64 = file.readText()
        Log.d("base64", base64)
//        Log.d("File", "File path: $file")
    }

    private fun setupAction() {
        binding.btnRephoto.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}