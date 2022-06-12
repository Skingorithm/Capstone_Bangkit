package com.example.capstonegas.view.detailhistory

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.capstonegas.Base64Util
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityDetailHistoryBinding
import com.example.capstonegas.model.DatalistsetHistory
import kotlin.math.roundToInt

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupActionDetail()
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

    @SuppressLint("SetTextI18n")
    private fun setupAction() {
        val data = intent.getParcelableExtra<DatalistsetHistory>("history") as DatalistsetHistory

        binding.imageResult.setImageBitmap(data.photo.let { Base64Util.convertStringToBitmap(it) })
        binding.scanDate.text = data.scanDate
        binding.numberResult.text = "${data.total}/100"

        binding.acneProgressBarResult.setProgress(data.jerawat)
        binding.acneDesc.text = data.jerawat.toString() + "%"
        if(data.jerawat > 75){
            binding.acneExplanation.text = resources.getString(R.string.above75, "Acne")
        }
        else if(data.jerawat in 50..75){
            binding.acneExplanation.text = resources.getString(R.string.fiftyUntil75, "Acne")
        }
        else{
            binding.acneExplanation.text = resources.getString(R.string.below50, "Acne")
        }

        binding.wrinkleProgressBarResult.setProgress(data.kerutan)
        binding.wrinkleDesc.text = data.jerawat.toString() + "%"
        if(data.kerutan > 75){
            binding.wrinkleExplanation.text = resources.getString(R.string.above75, "Wrinkle")
        }
        else if(data.kerutan in 50..75){
            binding.wrinkleExplanation.text = resources.getString(R.string.fiftyUntil75, "Wrinkle")
        }
        else{
            binding.wrinkleExplanation.text = resources.getString(R.string.below50, "Wrinkle")
        }

        binding.bspotProgressBarResult.setProgress(data.flekHitam)
        binding.bspotDesc.text = data.flekHitam.toString() + "%"
        if(data.flekHitam > 75){
            binding.bspotExplanation.text = resources.getString(R.string.above75, "B Spot")
        }
        else if(data.flekHitam in 50..75){
            binding.bspotExplanation.text = resources.getString(R.string.fiftyUntil75, "B Spot")
        }
        else{
            binding.bspotExplanation.text = resources.getString(R.string.below50, "B Spot")
        }

        binding.peyeProgressBarResult.setProgress(data.mataPanda)
        binding.peyeDesc.text = data.mataPanda.toString() + "%"
        if(data.mataPanda > 75){
            binding.peyeExplanation.text = resources.getString(R.string.above75, "P Eye")
        }
        else if(data.mataPanda in 50..75){
            binding.peyeExplanation.text = resources.getString(R.string.fiftyUntil75, "P Eye")
        }
        else{
            binding.peyeExplanation.text = resources.getString(R.string.below50, "P Eye")
        }
    }

    private fun setupActionDetail() {
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
}