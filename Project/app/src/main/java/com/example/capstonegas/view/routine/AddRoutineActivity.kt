package com.example.capstonegas.view.routine

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ActivityAddRoutineBinding
import com.example.capstonegas.model.UserPreference
import com.example.capstonegas.viewmodel.AddRoutineViewModel
import com.example.capstonegas.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class AddRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoutineBinding
    private lateinit var token: String
    private val viewModel: AddRoutineViewModel by viewModels{
        ViewModelFactory(UserPreference.getInstance(dataStore))
    }
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }
            binding.timeNull.text = formattedTime
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()

        viewModel.getUser().observe(this) {
            token = it.token
        }
    }

    private fun setupView()
    {
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
        binding.btnAddRoutine.setOnClickListener {
            val routineName = binding.routineFieldText.text.toString()
        }

        binding.timePickerButton.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                this,
                // listener to perform task
                // when time is picked
                timePickerDialogListener,
                // default hour when the time picker
                // dialog is opened
                12,
                // default minute when the time picker
                // dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )
            timePicker.show()
        }
    }
}