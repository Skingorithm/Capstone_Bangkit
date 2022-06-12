package com.example.capstonegas.view.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonegas.R
import com.example.capstonegas.adapter.ListHistoryAdapter
import com.example.capstonegas.databinding.FragmentProfileBinding
import com.example.capstonegas.model.DatalistsetHistory
import com.example.capstonegas.model.Datalistuser
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.view.welcome.WelcomeActivity
import com.example.capstonegas.viewmodel.ProfileViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    private lateinit var data: Datalistuser
    private lateinit var rvHistory: RecyclerView
    private val listHistory = ArrayList<DatalistsetHistory>()
    private var username: String = ""
    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()

        profileViewModel.getUser().observe(viewLifecycleOwner) {
            username = it.name
            token = it.token

            setupUser(username, token)
            setupDataUser()
        }

        rvHistory = binding.rvHistory
        rvHistory.setHasFixedSize(true)
        showListHistory(listHistory)

        profileViewModel.listHistory.observe(viewLifecycleOwner) {
            showListHistory(it)
        }

        showLoading(true)
        profileViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

    }


    private fun setupUser(user: String, token: String) {
        profileViewModel.getDataUser(user, token)
        profileViewModel.getHistoryByUsername(user, token)
    }

    private fun setupDataUser() {
        profileViewModel.dataUser.observe(viewLifecycleOwner) {
            if (it != null) {
                data = it
                showProfile(data)
            }
        }
    }

    private fun setupAction(){
        binding.imageButton.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.alert_title_logout))
                setMessage(getString(R.string.alert_msg_logout))
                setPositiveButton(getString(R.string.alert_btn_logout)) { _, _ ->
                    profileViewModel.logout()
                    val intent = Intent(context, WelcomeActivity::class.java)
                    startActivity(intent)
                    // finish activity
                    activity?.finish()
                }
                create()
                show()
            }
        }
    }

    private fun showProfile(data : Datalistuser) {
        Glide.with(this)
            .load(data.profilePicture)
            .circleCrop()
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.imgProfile)

        binding.fullName.text = data.fullName
        binding.username.text = data.username
    }

    private fun showListHistory(list: List<DatalistsetHistory>) {
        rvHistory.layoutManager = LinearLayoutManager(context)
        val adapter = ListHistoryAdapter(list)

        if (adapter.itemCount == 0) {
            binding.imgEmptyHistory.visibility = View.VISIBLE
            binding.emptyHistory.visibility = View.VISIBLE
        } else {
            rvHistory.adapter = adapter
            binding.imgEmptyHistory.visibility = View.GONE
            binding.emptyHistory.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarHistory.visibility =
            if (isLoading) View.VISIBLE
            else View.GONE
    }

}