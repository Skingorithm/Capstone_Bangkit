package com.example.capstonegas.view.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityRegisterBinding
import com.example.capstonegas.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
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
            val password = binding.passTextField.text.toString()
            when{
                name.isEmpty() -> binding.nameTextLayout.error = "Name is required"
                email.isEmpty() -> binding.emailTextLayout.error = "Email is required"
                password.isEmpty() -> binding.passTextLayout.error = "Password is required"
                else -> {
                    binding.nameTextLayout.error = null
                    binding.emailTextLayout.error = null
                    binding.passTextLayout.error = null
                }
            }
        }
        binding.textView6.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}