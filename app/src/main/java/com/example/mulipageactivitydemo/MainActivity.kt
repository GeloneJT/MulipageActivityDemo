package com.example.mulipageactivitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.*
import com.example.mulipageactivitydemo.R.color.midnight_blue

class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        loginButton.setBackgroundColor(getColor(this, midnight_blue))

        registerButton = findViewById(R.id.registerButton)
        registerButton.setBackgroundColor(getColor(this, R.color.hunter_green))

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