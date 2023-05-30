package com.example.teskifapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teskifapp.adapters.BAdapter
import com.example.teskifapp.models.BookingModel
import com.google.firebase.database.*

class ShowBookingActivity : AppCompatActivity() {

    private lateinit var bRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var bList: ArrayList<BookingModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_booking)

        bRecyclerView = findViewById(R.id.rvb)
        bRecyclerView.layoutManager = LinearLayoutManager(this)
        bRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        bList = arrayListOf<BookingModel>()

        getBookingData()
    }

    private fun getBookingData() {
        bRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("NewBooking")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bList.clear()
                if (snapshot.exists()) {
                    for (bSnap in snapshot.children) {
                        val bData = bSnap.getValue(BookingModel::class.java)
                        bList.add(bData!!)
                    }
                    val mAdapter = BAdapter(bList)
                    bRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : BAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ShowBookingActivity, bookDetails::class.java)

                            //put extras data

                            intent.putExtra("bId", bList[position].bId)
                            intent.putExtra("sService", bList[position].sService)
                            intent.putExtra("sPrice", bList[position].sPrice)
                            intent.putExtra("sDate", bList[position].sDate)
                            intent.putExtra("sTime", bList[position].sTime)
                            startActivity(intent)
                        }
                    })

                    bRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error appropriately
                TODO("Not yet implemented")
            }


        })
    }
}
