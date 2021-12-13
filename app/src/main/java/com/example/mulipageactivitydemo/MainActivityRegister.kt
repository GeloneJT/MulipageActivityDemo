package com.example.mulipageactivitydemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivityRegister : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var registerButton: Button
    private lateinit var nameEntered: EditText
    private lateinit var passwordEntered: EditText
    private lateinit var citySelect: EditText
    private lateinit var dobEntered: DatePicker
    private lateinit var dobText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        nextButton = findViewById(R.id.nextButton)
        registerButton = findViewById(R.id.registerButton)
        nameEntered = findViewById(R.id.namePlace)
        passwordEntered = findViewById(R.id.passwordPlace)
        citySelect = findViewById(R.id.cityPlace)
        dobEntered = findViewById(R.id.datePickerRegister)
        dobText = findViewById(R.id.dobText)

        registerButton.setBackgroundColor(Color.GREEN)
        nextButton.setBackgroundColor(Color.CYAN)


        val today = Calendar.getInstance()
        dobEntered.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            val month = month + 1
            dobText.text = "$month/$day/$year"
        }

        fun showAlertFail() {
            // Alert for user failing to enter information
            val alertFail = AlertDialog.Builder(this)
            alertFail.setTitle("No information entered")
            alertFail.setMessage("Please enter and name, password and city")
            alertFail.setIcon(android.R.drawable.ic_dialog_alert)

            alertFail.setPositiveButton("Try Again") { _, _ ->
                Toast.makeText(this, "Reenter name, password and city", Toast.LENGTH_LONG).show()
            }
            alertFail.setNegativeButton("Exit") { _, _ ->
                finish()
            }
            val myAlertFail: AlertDialog = alertFail.create()
            myAlertFail.show()
        }
        fun runRegisterIntent(rName:String,rPass:String,rCity:String,rDob:String){
            intent = Intent(this, MainActivityConfirmation::class.java)
            intent.putExtra("name", rName)
            intent.putExtra("password", rPass)
            intent.putExtra("city", rCity)
            intent.putExtra("dob", rDob)
            startActivity(intent)
        }
        fun runSharedPreference(rName:String,rPass: String,rCity:String,rDob:String){
            val sharedPref: SharedPreferences =
                this.getSharedPreferences("MyFile", Context.MODE_PRIVATE)
            val sharedPrefEdit: SharedPreferences.Editor = sharedPref.edit()
            sharedPrefEdit.putString("name", rName)
            sharedPrefEdit.putString("password", rPass)
            sharedPrefEdit.putString("city", rCity)
            sharedPrefEdit.putString("dob", rDob)
            sharedPrefEdit.apply()
        }

        registerButton.setOnClickListener {
            val rName = nameEntered.text.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val rPass = passwordEntered.text.toString()
            val rCity = citySelect.text.toString()
                .split(" ")
                .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
                .joinToString(" ")
            val rDob = dobText.text.toString()

            // Alert for user to confirm the information that was added is correct
            val alertSuccess = AlertDialog.Builder(this)
            alertSuccess.setTitle("Information confirmation")
            alertSuccess.setMessage("Is the information added correct?")
            alertSuccess.setIcon(android.R.drawable.ic_dialog_alert)

            alertSuccess.setPositiveButton("Yes") { _, _ ->
                runRegisterIntent(rName,rPass,rCity,rDob)
            }
            alertSuccess.setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Reenter incorrect information", Toast.LENGTH_LONG).show()
            }
            val myAlertSuccess: AlertDialog = alertSuccess.create()

            if (rName != "" && rPass != "" && rCity != "") {
                myAlertSuccess.show()
                runSharedPreference(rName,rPass,rCity,rDob)

            } else
                showAlertFail()
        }

        nextButton.setOnClickListener {
            intent = Intent(this, MainActivityStore::class.java)
            startActivity(intent)
        }
    }
}