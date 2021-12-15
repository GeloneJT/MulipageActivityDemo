package com.example.mulipageactivitydemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.*
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
        registerButton.setBackgroundColor(getColor(this, R.color.hunter_green))
        nextButton.setBackgroundColor(getColor(this, R.color.midnight_blue))
        /**
         * Instantiates the datePicker
         */
        val today = Calendar.getInstance()
        dobEntered.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            val month = month + 1
            dobText.text = "$month/$day/$year"
        }
        /**
         * Creates a UserInput class that serves a blueprint for the obj storing the entered information
         */
        class UserInput{
            val rName = nameEntered.text.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val rPass = passwordEntered.text.toString()
            val rCity = citySelect.text.toString()
                .split(" ")
                .joinToString(" ") { it1 -> it1.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
            val rDob = dobText.text.toString()
        }
        /**
         * Alert for user failing to enter information
         */
        fun showAlertFail() {
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
        /**
         * Alert for user successfully entering information.
         * Once that is confirmed an intent is executed moving the user to the next page
         */
        fun showAlertSuccess(){
            val userInfo = UserInput()
            // Alert for user to confirm the information that was added is correct
            val alertSuccess = AlertDialog.Builder(this)
            alertSuccess.setTitle("Information confirmation")
            alertSuccess.setMessage("Is the information added correct?")
            alertSuccess.setIcon(android.R.drawable.ic_dialog_alert)

            alertSuccess.setPositiveButton("Yes") { _, _ ->
                intent = Intent(this, MainActivityConfirmation::class.java)
                intent.putExtra("name", userInfo.rName)
                intent.putExtra("password", userInfo.rPass)
                intent.putExtra("city", userInfo.rCity)
                intent.putExtra("dob", userInfo.rDob)
                startActivity(intent)
            }
            alertSuccess.setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Reenter incorrect information", Toast.LENGTH_LONG).show()
            }
            val myAlertSuccess: AlertDialog = alertSuccess.create()
            myAlertSuccess.show()

        }
        /**
         * Generates SharedPreference data for user entered information
         */
        fun runSharedPreference(){
            val userInfo = UserInput()
            val sharedPref: SharedPreferences =
                this.getSharedPreferences("MyFile", Context.MODE_PRIVATE)
            val sharedPrefEdit: SharedPreferences.Editor = sharedPref.edit()
            sharedPrefEdit.putString("name", userInfo.rName)
            sharedPrefEdit.putString("password", userInfo.rPass)
            sharedPrefEdit.putString("city", userInfo.rCity)
            sharedPrefEdit.putString("dob", userInfo.rDob)
            sharedPrefEdit.apply()
        }
        /**
         * On pressing the register button checks the created UserInput obj for data.
         * If the requirements are satisfied the showAlertSuccess and runSharedPreference function are executed
         * If the requirement are not satisfied showAlertFail is executed prompting the user to enter information to continue
         */
        registerButton.setOnClickListener {
            val userInfo = UserInput()
            if (userInfo.rName != "" && userInfo.rPass != "" && userInfo.rCity != "" && userInfo.rDob != "") {
                showAlertSuccess()
                runSharedPreference()
            } else
                showAlertFail()
        }
        /**
         * On pressing the next button skips the registration process and displays the
         * final "Home" screen with default images and displays a Welcome message
         * accompanied with the name of the last user either logged in or registered successfully
         */
        nextButton.setOnClickListener {
            intent = Intent(this, MainActivityStore::class.java)
            startActivity(intent)
        }
    }
}