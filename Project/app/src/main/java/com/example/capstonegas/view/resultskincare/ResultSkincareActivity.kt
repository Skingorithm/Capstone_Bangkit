package com.example.capstonegas.view.resultskincare

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.capstonegas.Base64Util
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityResultSkincareBinding
import com.example.capstonegas.model.Output
import com.example.capstonegas.model.ResultData
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.ResultSkincareViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory
import java.io.File
import kotlin.math.roundToInt

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ResultSkincareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultSkincareBinding
    private lateinit var token: String
    private lateinit var userName: String
    private lateinit var data_0: Output
    private lateinit var data: ResultData
    private lateinit var base64: String

    private val viewModel: ResultSkincareViewModel by viewModels{
        ViewModelFactory(UserPreference.getInstance(dataStore))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultSkincareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        data_0 = intent.getParcelableExtra<Output>(ML_DATA) as Output
        openFile()
        data = data_0.average?.let { data_0.acne?.let { it1 ->
            data_0.peye?.let { it2 ->
                data_0.wrinkle?.let { it3 ->
                    data_0.bspot?.let { it4 ->
                        ResultData(it,
                            it1, it2, it3, it4, base64)
                    }
                }
            }
        } }!!

        binding.imageResult.setImageBitmap(data.image.let { Base64Util.convertStringToBitmap(it) })
        binding.numberResult.text = "${data.average.roundToInt()}/100"

        data.acne.let { binding.acneProgressBarResult.progress = it.roundToInt() }
        binding.acneDesc.text = data.acne.toString() + "%"
        if(data.acne.roundToInt() > 75){
            binding.acneExplanation.text = resources.getString(R.string.above75, "Acne")
        }
        else if(data.acne.roundToInt() in 50..75){
            binding.acneExplanation.text = resources.getString(R.string.fiftyUntil75, "Acne")
        }
        else{
            binding.acneExplanation.text = resources.getString(R.string.below50, "Acne")
        }

        data.wrinkle.let { binding.wrinkleProgressBarResult.progress = it.roundToInt() }
        binding.wrinkleDesc.text= data.wrinkle.toString() + "%"
        if(data.wrinkle.roundToInt() > 75){
            binding.wrinkleExplanation.text = resources.getString(R.string.above75, "Kerutan")
        }
        else if(data.wrinkle.roundToInt() in 50..75){
            binding.wrinkleExplanation.text = resources.getString(R.string.fiftyUntil75, "Kerutan")
        }
        else{
            binding.wrinkleExplanation.text = resources.getString(R.string.below50, "Kerutan")
        }

        data.bspot.let { binding.bspotProgressBarResult.progress = it.roundToInt() }
        binding.bspotDesc.text = data.bspot.toString() + "%"
        if(data.bspot.roundToInt() > 75){
            binding.bspotExplanation.text = resources.getString(R.string.above75, "Flek Hitam")
        }
        else if(data.bspot.roundToInt() in 50..75){
            binding.bspotExplanation.text = resources.getString(R.string.fiftyUntil75, "Flek Hitam")
        }
        else{
            binding.bspotExplanation.text = resources.getString(R.string.below50, "Flek Hitam")
        }

        data.peye.let { binding.peyeProgressBarResult.progress = it.roundToInt() }
        binding.peyeDesc.text = data.peye.toString() + "%"
        if(data.peye.roundToInt() > 75){
            binding.peyeExplanation.text = resources.getString(R.string.above75, "Mata Panda")
        }
        else if(data.peye.roundToInt() in 50..75){
            binding.peyeExplanation.text = resources.getString(R.string.fiftyUntil75, "Mata Panda")
        }
        else{
            binding.peyeExplanation.text = resources.getString(R.string.below50, "Mata Panda")
        }

        binding.progressBar2.visibility = View.GONE
        viewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        }

        viewModel.isError.observe(this) {
            if (it == true) {
                binding.progressBar2.visibility = View.GONE
                //Tost that display error uploading data
                Toast.makeText(this, "Error uploading data", Toast.LENGTH_SHORT).show()
            }
            else if(it == false){
                binding.progressBar2.visibility = View.GONE
                finish()
            }
        }

        setupView()
        setupAction(data)
    }

    private fun openFile(){
        val path = applicationContext.filesDir
        val letDirectory = File(path, "Picture Base64")
        val file = File(letDirectory, "fotoku.txt")
        base64 = file.readText()
//        Log.d("base64", base64)
//        Log.d("File", "File path: $file")
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction(data: ResultData) {
        binding.acneExpandButton.setOnClickListener {
            if (binding.acneExpandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.acneCardView, AutoTransition())
                binding.acneExpandableLayout.visibility = View.VISIBLE
                binding.acneExpandButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                TransitionManager.beginDelayedTransition(binding.acneCardView, AutoTransition())
                binding.acneExpandableLayout.visibility = View.GONE
                binding.acneExpandButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

        binding.wrinkleExpandButton.setOnClickListener {
            if (binding.wrinkleExpandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.wrinkleCardView, AutoTransition())
                binding.wrinkleExpandableLayout.visibility = View.VISIBLE
                binding.wrinkleExpandButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                TransitionManager.beginDelayedTransition(binding.wrinkleCardView, AutoTransition())
                binding.wrinkleExpandableLayout.visibility = View.GONE
                binding.wrinkleExpandButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

        binding.bspotExpandButton.setOnClickListener {
            if (binding.bspotExpandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.bspotCardView, AutoTransition())
                binding.bspotExpandableLayout.visibility = View.VISIBLE
                binding.bspotExpandButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                TransitionManager.beginDelayedTransition(binding.bspotCardView, AutoTransition())
                binding.bspotExpandableLayout.visibility = View.GONE
                binding.bspotExpandButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

        binding.peyeExpandButton.setOnClickListener {
            if (binding.peyeExpandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.peyeCardView, AutoTransition())
                binding.peyeExpandableLayout.visibility = View.VISIBLE
                binding.peyeExpandButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                TransitionManager.beginDelayedTransition(binding.peyeCardView, AutoTransition())
                binding.peyeExpandableLayout.visibility = View.GONE
                binding.peyeExpandButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

        binding.btnNotSaveResult.setOnClickListener {
            finish()
        }

        binding.btnSaveResult.setOnClickListener {
            viewModel.getUser().observe(this) {
                token = it.token
                userName = it.name
                viewModel.postHistory(token, userName, data)
            }
        }
    }

    companion object {
        const val ML_DATA = "ML_DATA"
    }
}