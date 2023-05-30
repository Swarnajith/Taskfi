package com.example.teskifapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WTeskifActivity : AppCompatActivity() {

    private lateinit var btnSkipD : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wteskif)

        btnSkipD = findViewById(R.id.skipBtnD)

        // skip and continue to main screen
        btnSkipD.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}