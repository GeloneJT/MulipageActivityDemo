package com.example.mulipageactivitydemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivityConfirmation : AppCompatActivity() {

    private lateinit var infoDisplay: TextView
    private lateinit var okayButton: Button
    private lateinit var doneButton: Button
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder

    private val channelId = "my.jelani.notification"
    private val description = "Test Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_confirmation)

        infoDisplay = findViewById(R.id.displayVal)
        okayButton = findViewById(R.id.okayButton)
        doneButton = findViewById(R.id.doneButton)

        val sharedPref: SharedPreferences =
            this.getSharedPreferences("MyFile", Context.MODE_PRIVATE)

        val regName = sharedPref.getString("name", "N/A")
        val regPass = sharedPref.getString("password", "N/A")
        val regCity = sharedPref.getString("city", "N/A")
        val regDob = sharedPref.getString("dob","N/A" )

        "Hello $regName \n Set password is: $regPass \n Saved city is: $regCity \n Birthday is: $regDob ".also {
            infoDisplay.text = it
        }
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        okayButton.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("ABC Demo Company Alert")
            alert.setMessage(
                "Your information has been saved.  \n" +
                        "Click Exit to return to registration."
            )
            alert.setIcon(android.R.drawable.ic_dialog_alert)

            alert.setPositiveButton("Okay") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel =
                        NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)
                    notificationManager.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(this, channelId)
                        .setContentTitle("ABC Demo Company")
                        .setContentText("Your information has been saved successfully")
                        .setSmallIcon(android.R.drawable.btn_star_big_on)
                        .setAutoCancel(true)
                }
                notificationManager.notify(8793, builder.build())
            }
            alert.setNegativeButton("Exit") { _, _ ->
                intent = Intent(this, MainActivityRegister::class.java)
                startActivity(intent)
            }
            val myAlert: AlertDialog = alert.create()
            myAlert.show()
        }

        doneButton.setOnClickListener {
            intent = Intent(this, MainActivityStore::class.java)
            startActivity(intent)
        }
    }
}