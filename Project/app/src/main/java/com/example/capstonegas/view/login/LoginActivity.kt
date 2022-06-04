package com.example.capstonegas.view.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.capstonegas.databinding.ActivityLoginBinding
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.view.main.MainActivity
import com.example.capstonegas.view.register.RegisterActivity
import com.example.capstonegas.viewmodel.LoginViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(
            UserPreference.getInstance(dataStore)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()

        showLoading(false)
        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(it: Boolean?) {
        binding.progressBar.visibility = if (it == true) View.VISIBLE else View.GONE
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

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameTextFieldText.text.toString()
            val password = binding.passTextFieldText.text.toString()
            when {
                username.isEmpty() -> {
                    binding.usernameTextField.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passTextField.error = "Masukkan password"
                }
                else -> {
                    loginViewModel.postLogin(username, password)
                    loginViewModel.loginSuccess.observe(this){
                        if(it == true) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else if(it == false){
                            AlertDialog.Builder(this).apply {
                                setTitle("Oops!")
                                setMessage("Email/Password Salah.")
                                setPositiveButton("Coba lagi") { _, _ ->
                                    // do nothing
                                }
                                create()
                                show()
                            }
                        }
                    }
                }
            }
        }
        binding.textView4.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}