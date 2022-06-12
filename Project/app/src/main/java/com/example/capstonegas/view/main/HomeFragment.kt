package com.example.capstonegas.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.capstonegas.R
import com.example.capstonegas.databinding.FragmentHomeBinding
import com.example.capstonegas.model.DatalistsetHistory
import com.example.capstonegas.model.Datalistuser
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.view.camera.UploadActivity
import com.example.capstonegas.viewmodel.HomeViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.renderer.YAxisRenderer

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply { viewLifecycleOwner
        HomeViewModel}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getUser().observe(viewLifecycleOwner) {
            binding.username.text = getString(R.string.userName, it.name)

            setupUser(it.name, it.token)
            setupDataUser()
        }

        setupAction()
    }

    private fun setupUser(name: String, token: String) {
        homeViewModel.getDataUser(name, token)
        homeViewModel.getHistoryByUsername(name, token)
    }

    private fun setupDataUser() {
        homeViewModel.dataUser.observe(viewLifecycleOwner) {
            if (it != null) {
                showProfileImage(it)
            }
        }

        homeViewModel.listHistory.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                showGraph(it)
            } else {
                binding.lineChart.visibility = View.GONE
                binding.imageChart.visibility = View.VISIBLE
                binding.textImageChart.visibility = View.VISIBLE
            }
        }
    }

    private fun showProfileImage(data : Datalistuser) {
        Glide.with(this)
            .load(data.profilePicture)
            .circleCrop()
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.imageProfile)
    }

    private fun showGraph(history : List<DatalistsetHistory>) {

        val xvalue = ArrayList<String>()
        val lineEntry = ArrayList<Entry>()
        var index = 0

        history.forEach {
            xvalue.add(it.scanDate)
            lineEntry.add(Entry(it.total.toFloat(), index))
            index += 1
        }

        val lineDataSet = LineDataSet(lineEntry, "Nilai Wajah")
        lineDataSet.color = resources.getColor(R.color.primary_blue)
        lineDataSet.circleRadius = 5f
        lineDataSet.setCircleColor(resources.getColor(R.color.primary_blue))
        lineDataSet.valueTextSize = 12f
        lineDataSet.valueTextColor = resources.getColor(R.color.black)
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = resources.getColor(R.color.primary_blue)
        lineDataSet.fillAlpha = 30

        val data = LineData(xvalue, lineDataSet)

        binding.lineChart.data = data
        binding.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.lineChart.animateXY(3000, 3000)
    }

    private fun setupAction() {
        binding.imgButtonCheckFace.setOnClickListener {
            val intent = Intent(context, UploadActivity::class.java)
            startActivity(intent)
        }
    }
}