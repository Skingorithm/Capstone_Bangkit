package com.example.capstonegas.view.routine

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
    private var token: String? = null
    private var userName: String? = null
    private val viewModel: AddRoutineViewModel by viewModels{
        ViewModelFactory(UserPreference.getInstance(dataStore))
    }
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "0${hourOfDay}:0${minute}"
                    } else {
                        "0${hourOfDay}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute}"
                    } else {
                        "${hourOfDay}:${minute}"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute}"
                    } else {
                        "${hourOfDay}:${minute}"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute}"
                    } else {
                        "${hourOfDay}:${minute}"
                    }
                }
            }
            binding.timeNull.text = formattedTime
        }

    private val datePickerDialogListener: android.app.DatePickerDialog.OnDateSetListener =
        android.app.DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val formattedDate = "${year}/${month + 1}/${dayOfMonth}"
            binding.dateNull.text = formattedDate
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUser().observe(this) {
            token = it.token
        }

        setupAction()

        val actionBar = supportActionBar

        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            actionBar.title = "Add Routine"
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        viewModel.getUser().observe(this) {
            token = it.token
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

//    private fun setupView()
//    {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }

    private fun setupAction() {
        binding.btnAddRoutine.setOnClickListener {
            val routinityName = binding.routineFieldText.text.toString()
            val notifyHour = binding.timeNull.text.toString()
            val alarmDate = binding.dateNull.text.toString()
            val fifteenBefore = binding.checkBoxFifteen.isChecked
            val thirtyBefore = binding.checkBoxThirty.isChecked
            val repeatValue = when (binding.opsi.checkedRadioButtonId) {
                R.id.radioButton_no_repeat-> 0
                R.id.radioButton_repeat_weekly-> 1
                else -> 0
            }
            when{
                routinityName.isEmpty() -> binding.routineFieldText.error = "Routine name is required"
                notifyHour == "XX:XX" -> binding.timeNull.error = "Notify time is required"
                alarmDate == "YYYY/MM/DD" -> binding.dateNull.error = "Alarm date is required"
                else -> {
                    token?.let { it1 -> userName?.let { it2 ->
                        viewModel.postRoutine(it1, routinityName, notifyHour, alarmDate, fifteenBefore, thirtyBefore, repeatValue,
                            it2
                        )
                    } }
                    finish()
                }
            }

        }

        binding.timePickerButton.setOnClickListener {
            val timePicker= TimePickerDialog(
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

        binding.datePickerButton.setOnClickListener {
            val datePicker: android.app.DatePickerDialog = android.app.DatePickerDialog(
                // pass the Context
                this,
                // listener to perform task
                // when date is picked
                datePickerDialogListener,
                // default year when the date picker
                // dialog is opened
                2022,
                // default month when the date picker
                // dialog is opened (0-11)
                1,
                // default day of month when the date picker
                // dialog is opened
                1
            )
            datePicker.show()
        }
    }
}