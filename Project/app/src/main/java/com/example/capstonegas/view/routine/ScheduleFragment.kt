package com.example.capstonegas.view.routine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capstonegas.R
import com.example.capstonegas.databinding.FragmentScheduleBinding
import com.example.capstonegas.viewmodel.ScheduleViewModel

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ScheduleViewModel()

        setupAction()
    }

    private fun setupAction() {
        binding.addRoutineButton.setOnClickListener {
            val intent = Intent(activity, AddRoutineActivity::class.java)
            startActivity(intent)
        }
    }
}