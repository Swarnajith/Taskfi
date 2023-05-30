package com.example.teskifapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class TeskifActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teskif)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, WTeskifActivity::class.java))
        }, 2000) // Delay for 2 seconds (2000 milliseconds)

    }
}