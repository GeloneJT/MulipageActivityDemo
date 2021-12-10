package com.example.mulipageactivitydemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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

        loginButton.setBackgroundColor(Color.BLUE)
        homeButton.setBackgroundColor(Color.RED)


        loginButton.setOnClickListener {
            val validName = userName.text.toString()
            val validPassword = userPassword.text.toString()
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Check name and password!")
            alert.setMessage("Reenter login credentials?")
            alert.setIcon(android.R.drawable.ic_dialog_alert)

            alert.setPositiveButton("Try Again") { _, _ ->
                Toast.makeText(this, "Reenter name,password and city", Toast.LENGTH_SHORT).show()
            }
            alert.setNegativeButton("Exit") { _, _ ->
                finish()
            }
            val myAlert: AlertDialog = alert.create()

            if (validName == "admin" && validPassword == "admin123") {
                intent = Intent(this, MainActivityStore::class.java)
                intent.putExtra("name", validName)
                intent.putExtra("password", validPassword)
                startActivity(intent)
            } else
                myAlert.show()
        }

        homeButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}