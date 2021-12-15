package com.example.mulipageactivitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.*
import com.example.mulipageactivitydemo.R.color.hunter_green
import com.example.mulipageactivitydemo.R.color.midnight_blue

class MainActivityLogin : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var userPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        userName = findViewById(R.id.name)
        userPassword = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        homeButton = findViewById(R.id.homeButton)

        loginButton.setBackgroundColor(getColor(this, midnight_blue))
        homeButton.setBackgroundColor(getColor(this, hunter_green))

        fun showBadAlert() {
            val alertFail = AlertDialog.Builder(this)
            alertFail.setTitle("Check name and password!")
            alertFail.setMessage("Reenter login credentials?")
            alertFail.setIcon(android.R.drawable.ic_dialog_alert)

            alertFail.setPositiveButton("Try Again") { _, _ ->
                Toast.makeText(this, "Reenter Name and Password", Toast.LENGTH_SHORT).show()
            }
            alertFail.setNegativeButton("Exit") { _, _ ->
                finish()
            }
            val myAlertFail: AlertDialog = alertFail.create()
            myAlertFail.show()
        }

        loginButton.setOnClickListener {
            val validName = userName.text.toString()
            val validPassword = userPassword.text.toString()


            if (validName == "admin" && validPassword == "admin123") {
                intent = Intent(this, MainActivityStore::class.java)
                intent.putExtra("name", validName)
                intent.putExtra("password", validPassword)
                startActivity(intent)
            } else
                showBadAlert()

        }

        homeButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}