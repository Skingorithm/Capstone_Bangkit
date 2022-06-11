package com.example.capstonegas.view.resultskincare

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.capstonegas.Base64Util
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityResultSkincareBinding
import com.example.capstonegas.databinding.ItemResultSkincareBinding
import com.example.capstonegas.model.MLResponse
import com.example.capstonegas.model.Output
import com.example.capstonegas.model.ResultData
import kotlin.math.roundToInt

class ResultSkincareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultSkincareBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultSkincareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        val data = intent.getParcelableExtra<ResultData>(ML_DATA) as ResultData

        binding.imageResult.setImageBitmap(data.image?.let { Base64Util.convertStringToBitmap(it) })
        binding.numberResult.text = "${data.average?.roundToInt()}/100"

        data.acne?.let { binding.acneProgressBarResult.setProgress(it.roundToInt()) }
        binding.acneDesc.text = data.acne?.toString() + "%"

        data.wrinkle?.let { binding.wrinkleProgressBarResult.setProgress(it.roundToInt()) }
        binding.wrinkleDesc.text= data.wrinkle?.toString() + "%"

        data.bspot?.let { binding.bspotProgressBarResult.setProgress(it.roundToInt()) }
        binding.bspotDesc.text = data.bspot?.toString() + "%"

        data.peye?.let { binding.peyeProgressBarResult.setProgress(it.roundToInt()) }
        binding.peyeDesc.text = data.peye?.toString() + "%"

        setupView()
        setupAction()
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

    private fun setupAction(){
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
    }

    companion object {
        const val ML_DATA = "ML_DATA"
    }
}