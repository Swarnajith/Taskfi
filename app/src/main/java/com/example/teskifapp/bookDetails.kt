package com.example.teskifapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.teskifapp.models.BookingModel
import com.google.firebase.database.FirebaseDatabase

class bookDetails : AppCompatActivity() {
    private lateinit var tvbID : TextView
    private lateinit var tvbService : TextView
    private lateinit var tvbPrice : TextView
    private lateinit var tvbDate : TextView
    private lateinit var tvbTime : TextView

    private lateinit var btnUpdate : Button
    private lateinit var btnDelete: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("bId").toString(),

            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("bId").toString()
            )
        }
    }


    private fun openUpdateDialog(
        bId: String,
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updateb, null)

        mDialog.setView(mDialogView)

        val bService = mDialogView.findViewById<TextView>(R.id.bService)
        val bDate = mDialogView.findViewById<EditText>(R.id.bDate)
        val bTime = mDialogView.findViewById<EditText>(R.id.bTime)
        val bPrice = mDialogView.findViewById<EditText>(R.id.bPrice)


        val btnUpdate = mDialogView.findViewById<Button>(R.id.btnUpdate)
/*
        bService.setText(intent.getStringExtra(" bService").toString())
        bDate.setText(intent.getStringExtra("bDate").toString())
        bTime.setText(intent.getStringExtra("bTime").toString())
        bPrice.setText(intent.getStringExtra("bPrice").toString())*/



        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener {
            updateBData(
                bId,
                bService.text.toString(),
                bDate.text.toString(),
                bTime.text.toString(),
                bPrice.text.toString()
            )

            Toast.makeText(applicationContext, "Booking $bService Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvbService.text = bService.text.toString()
            tvbDate.text = bDate.text.toString()
            tvbTime.text = bTime.text.toString()
            tvbPrice.text = bPrice.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateBData(
         id: String,
         Service: String,
         Date: String,
         Time: String,
         Price: String
    ) {

        val dbRef = FirebaseDatabase.getInstance().getReference("NewBooking").child(id)
        val bInfo = BookingModel(id, Service, Date, Time, Price)
        dbRef.setValue(bInfo)
    }


    private fun initView() {
        tvbID = findViewById(R.id.bId)
        tvbService = findViewById(R.id.bService)
        tvbPrice = findViewById(R.id.bPrice)
        tvbDate = findViewById(R.id.bDate)
        tvbTime = findViewById(R.id.bTime)

        btnDelete = findViewById(R.id.btnDelete)
        btnUpdate = findViewById(R.id.btnUpdate)
    }

    private fun setValuesToViews() {
        tvbID.text= intent.getStringExtra("bId")
        tvbService.text = intent.getStringExtra("sService")
        tvbPrice.text = intent.getStringExtra("sPrice")
        tvbDate.text = intent.getStringExtra("sDate")
        tvbTime.text = intent.getStringExtra("sTime")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("NewBooking").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Booking data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ShowBookingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}