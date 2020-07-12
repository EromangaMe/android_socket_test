package me.sagiri.me.socket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private lateinit var hostEditView : EditText
    private  lateinit var portEditView  : EditText
    private lateinit var nameEditView : EditText
    private  lateinit var connectButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hostEditView = findViewById(R.id.hostEditView)
        portEditView = findViewById(R.id.portEditView)
        nameEditView = findViewById(R.id.nameEditView)
        connectButton = findViewById(R.id.connectButton)

        connectButton.setOnClickListener {
            val host = hostEditView.text.toString()
            val port = portEditView.text.toString()
            val name = nameEditView.text.toString()
            val i = Intent(applicationContext, ClientServer::class.java).apply {
                putExtra("host", host)
                putExtra("port", port)
                putExtra("name", name)
            }
            startService(i)
        }

    }
}