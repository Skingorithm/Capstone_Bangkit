package com.example.capstonegas.view.main

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityMainBinding
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.view.camera.UploadActivity
import com.example.capstonegas.view.welcome.WelcomeActivity
import com.example.capstonegas.viewmodel.MainViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels{
        ViewModelFactory(UserPreference.getInstance(dataStore))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        setupView()
        setupViewModel()
        setupAction()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
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

    private fun setupViewModel(){
        mainViewModel.getUser().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
//            else{
//                token = user.token
//            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                //TODO: go to home fragment
                return true
            }
            R.id.navigation_routines -> {
                //TODO: go to routines fragment
                return true
            }
            R.id.navigation_ingredients -> {
                //TODO: go to ingredients fragment
                return true
            }
            R.id.navigation_profile -> {
                //TODO: go to profile fragment
                return true
            }
            else -> return true
        }
    }

    private fun setupAction(){
        binding.fab.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }
    }
}