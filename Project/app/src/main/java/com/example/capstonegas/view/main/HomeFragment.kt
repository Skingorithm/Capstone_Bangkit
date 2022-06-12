package com.example.capstonegas.view.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.capstonegas.R
import com.example.capstonegas.databinding.FragmentHomeBinding
import com.example.capstonegas.model.Datalistuser
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.HomeViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

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
    }

    private fun setupUser(name: String, token: String) {
        homeViewModel.getDataUser(name, token)
    }

    private fun setupDataUser() {
        homeViewModel.dataUser.observe(viewLifecycleOwner) {
            if (it != null) {
                showProfileImage(it)
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
}