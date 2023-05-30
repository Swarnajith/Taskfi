package com.example.teskifapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var btnDisplayData:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsertData = findViewById(R.id.btnInsertData)
        btnDisplayData = findViewById(R.id.btnDisplayData)

        // skip and continue to main screen
        btnInsertData.setOnClickListener{
            val intent = Intent(this, AddBookingActivity::class.java)
            startActivity(intent)
        }
        btnDisplayData.setOnClickListener{
            val intent = Intent(this, ShowBookingActivity::class.java)
            startActivity(intent)
        }
    }
}