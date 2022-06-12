package com.example.capstonegas.view.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityRegisterBinding
import com.example.capstonegas.view.login.LoginActivity
import com.example.capstonegas.viewmodel.RegisterViewModel
import com.google.android.material.animation.AnimatorSetCompat.playTogether

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        setupView()
        setupAction()
    }

    private fun playAnimation(){
        val textView = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(500)
        val textView2 = ObjectAnimator.ofFloat(binding.textView2, View.ALPHA, 1f).setDuration(500)
        val imageView4 = ObjectAnimator.ofFloat(binding.imageView4, View.ALPHA, 1f).setDuration(500)
        val nameTextLayout = ObjectAnimator.ofFloat(binding.nameTextLayout, View.ALPHA, 1f).setDuration(500)
        val usernameTextLayout = ObjectAnimator.ofFloat(binding.usernameTextLayout, View.ALPHA, 1f).setDuration(500)
        val emailTextLayout = ObjectAnimator.ofFloat(binding.emailTextLayout, View.ALPHA, 1f).setDuration(500)
        val passTextLayout = ObjectAnimator.ofFloat(binding.passTextLayout, View.ALPHA, 1f).setDuration(500)
        val registerButton = ObjectAnimator.ofFloat(binding.registerButton, View.ALPHA, 1f).setDuration(500)
        val textView5 = ObjectAnimator.ofFloat(binding.textView5, View.ALPHA, 1f).setDuration(500)
        val textView6 = ObjectAnimator.ofFloat(binding.textView6, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(registerButton, textView5, textView6)
        }

        AnimatorSet().apply {
            playSequentially(textView, textView2, imageView4, nameTextLayout, usernameTextLayout, emailTextLayout, passTextLayout, together)
            start()
        }
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