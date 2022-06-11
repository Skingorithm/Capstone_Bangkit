package com.example.capstonegas.view.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.capstonegas.databinding.FragmentProfileBinding
import com.example.capstonegas.model.Datalistuser
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.MainViewModel
import com.example.capstonegas.viewmodel.ProfileViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private lateinit var data: Datalistuser
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply { viewLifecycleOwner
        ProfileViewModel}

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "1")
        profileViewModel.getUser().observe(viewLifecycleOwner) {
            Log.d(TAG, "2")
            username = it.name
            Log.d("isi username:", username)
        }

        if (username != "") {
            Log.d(TAG, "3")
            setupUser(username)
            Log.d(TAG, "5")
            setupDataUser()
        }
    }

    private fun setupUser(user: String) {
        Log.d(TAG,"4")
        profileViewModel.getDataUser(user)
    }

    private fun setupDataUser() {
        Log.d(TAG,"6")
        profileViewModel.dataUser.observe(viewLifecycleOwner) {
            Log.d("isi data user", it.toString())
            if (it != null) {
                Log.d("isi data user2", it.toString())
                data = it
                showProfile(data)
            }
        }
    }

    private fun showProfile(data : Datalistuser) {
        Log.d(TAG, "7")
        Glide.with(this)
            .load(data.profilePicture)
            .circleCrop()
            .into(binding.imgProfile)

        binding.fullName.text = data.fullName
        binding.username.text = data.username


    }

    companion object {
        private const val TAG = "ProfileFragment"
    }

}