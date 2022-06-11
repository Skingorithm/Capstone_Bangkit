package com.example.capstonegas.view.resultskincare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ItemResultSkincareBinding
import com.example.capstonegas.model.MLResponse
import com.example.capstonegas.model.Output

class ResultSkincareActivity : AppCompatActivity() {

    private lateinit var binding: ItemResultSkincareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemResultSkincareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Output>(ML_DATA) as Output
        Log.d("ResultSkincareActivity", "data: $data")

        binding.expandButton.setOnClickListener {
            if (binding.expandableLayout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.expandableLayout.visibility = View.VISIBLE
                binding.expandButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.expandableLayout.visibility = View.GONE
                binding.expandButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

    }

    companion object {
        const val ML_DATA = "ML_DATA"
    }
}