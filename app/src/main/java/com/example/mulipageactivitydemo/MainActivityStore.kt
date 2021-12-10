package com.example.mulipageactivitydemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivityStore : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView
    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_store)

        welcomeTextView =findViewById(R.id.welcomeMessage)
        homeButton = findViewById(R.id.homeButton)
        homeButton.setBackgroundColor(Color.RED)

        val sharedPref: SharedPreferences =
            this.getSharedPreferences("MyFile", Context.MODE_PRIVATE)
        val regName = sharedPref.getString("name", "N/A")
        val b: Bundle? = intent.extras
        val name = b?.get("name")

        if (name == "admin"){
            "Welcome $name".also { welcomeTextView.text = it }
        }
        else
            "Welcome $regName".also { welcomeTextView.text = it }



        homeButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}