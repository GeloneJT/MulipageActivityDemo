package com.example.mulipageactivitydemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        loginButton.setBackgroundColor(Color.BLUE)

        registerButton = findViewById(R.id.registerButton)
        registerButton.setBackgroundColor(Color.GREEN)

        loginButton.setOnClickListener {
            intent = Intent(this, MainActivityLogin::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener{
            intent = Intent(this, MainActivityRegister::class.java)
            startActivity(intent)
        }
    }
}