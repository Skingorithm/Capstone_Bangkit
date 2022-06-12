package com.example.capstonegas.view.routine

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.adapter.ListRoutineAdapter
import com.example.capstonegas.databinding.FragmentScheduleBinding
import com.example.capstonegas.model.AlarmItem
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.ScheduleViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory
import java.text.DateFormat.getDateInstance

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="user_preferences")
class ScheduleFragment() : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAlarm: RecyclerView
    private val viewModel: ScheduleViewModel by viewModels{
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAlarm = binding.rvMorning
        rvAlarm.setHasFixedSize(true)

        binding.dateSchedule.text = getDateInstance().format(System.currentTimeMillis())

        viewModel.getUser().observe(viewLifecycleOwner) {
            if(it!=null){
                val now = getDateInstance().format(System.currentTimeMillis())
                viewModel.postAlarm(it.token, it.name, now)
            }
        }

        viewModel.listAlarm.observe(viewLifecycleOwner) {
            if (it != null) {
                if(it.isNotEmpty()){
                    showAlarmList(it as ArrayList<AlarmItem>)
                } else{
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView11.visibility = View.VISIBLE
                }
            }
            else{
                binding.imageView.visibility = View.VISIBLE
                binding.textView11.visibility = View.VISIBLE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it!=null){
                showProgressBar(it)
            }
        }

        setupAction()
    }

    private fun showAlarmList(listAlarm: ArrayList<AlarmItem>){
        rvAlarm.layoutManager = LinearLayoutManager(requireContext())
        rvAlarm.adapter = ListRoutineAdapter(listAlarm)
        binding.textSkincareRoutine2.text = listAlarm.size.toString()
    }

    //function to show progress bar or not
    private fun showProgressBar(show: Boolean) {
        if (show) {
            binding.progressBar4.visibility = View.VISIBLE
        } else {
            binding.progressBar4.visibility = View.GONE
        }
    }

    private fun setupAction() {
        binding.addRoutineButton.setOnClickListener {
            val intent = Intent(activity, AddRoutineActivity::class.java)
            startActivity(intent)
        }
    }
}