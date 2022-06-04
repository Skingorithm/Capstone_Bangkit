package com.example.capstonegas.view.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityRegisterBinding
import com.example.capstonegas.view.login.LoginActivity
import com.example.capstonegas.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
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
        binding.registerButton.setOnClickListener {
            val name = binding.nameTextField.text.toString()
            val email = binding.emailTextField.text.toString()
            val userName = binding.usernameTextField.text.toString()
            val password = binding.passTextField.text.toString()
            when{
                name.isEmpty() -> binding.nameTextLayout.error = "Name is required"
                email.isEmpty() -> binding.emailTextLayout.error = "Email is required"
                email.isNotEmpty() && !email.contains("@") -> binding.emailTextLayout.error = "Email is invalid"
                userName.isEmpty() -> binding.usernameTextLayout.error = "Username is required"
                password.isEmpty() -> binding.passTextLayout.error = "Password is required"
                password.isNotEmpty() && password.length < 6 -> binding.passTextLayout.error = "Password must be at least 6 characters"
                else -> {
                    registerViewModel.postUser(name, userName, email, password)
                    registerViewModel.registerSuccess.observe(this ){
                        if (it) {
                            AlertDialog.Builder(this).apply {
                                setTitle("Yeah!")
                                setMessage("Akunnya sudah dibuat.")
                                setPositiveButton("Lanjut") { _, _ ->
                                    finish()
                                }
                                create()
                                show()
                            }
                        } else {
                            AlertDialog.Builder(this).apply {
                                setTitle("Oops!")
                                setMessage("Akunnya gagal dibuat.")
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
        binding.textView6.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}