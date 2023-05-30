package com.example.teskifapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teskifapp.models.BookingModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.System.err

class AddBookingActivity : AppCompatActivity() {

    private lateinit var bService : EditText
    private lateinit var bDate : EditText
    private lateinit var bTime : EditText
    private lateinit var bPrice : EditText
    
    private lateinit var subBtn : Button
    private lateinit var canBtn : Button

    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_booking)


        bService = findViewById(R.id.bService)
        bDate = findViewById(R.id.bDate)
        bTime = findViewById(R.id.bTime)
        bPrice = findViewById(R.id.bPrice)

        subBtn = findViewById(R.id.subBtn)
        canBtn = findViewById(R.id.canBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("NewBooking")

        subBtn.setOnClickListener{
            saveBookingData()
        }
    }
    private fun saveBookingData(){

        val sService = bService.text.toString()
        val sDate = bDate.text.toString()
        val sTime = bTime.text.toString()
        val sPrice = bPrice.text.toString()


        if (sService.isEmpty()){
            bService.error ="Please enter Service"
        }
        if (sDate.isEmpty()){
            bDate.error ="Please enter Date"
        }
        if (sTime.isEmpty()){
            bTime.error ="Please enter Time"
        }
        if (sPrice.isEmpty()){
            bPrice.error ="Please enter Price"
        }
        val bId = dbRef.push().key !!
        val newbooking = BookingModel(bId,sService, sDate, sTime, sPrice)

        dbRef.child(bId).setValue(newbooking)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                bService.text.clear()
                bDate.text.clear()
                bTime.text.clear()
                bPrice.text.clear()

            }.addOnFailureListener{ error ->
                Toast.makeText(this, "Error${err}", Toast.LENGTH_LONG).show()
            }
    }
}


