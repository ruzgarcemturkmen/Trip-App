package com.example.tripapp.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripapp.Adapter.TripsAdapter
import com.example.tripapp.Model.Trip
import com.example.tripapp.databinding.ActivityResultBinding
import com.google.firebase.database.*

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var from: String
    private lateinit var to: String
    private lateinit var date: String

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        database = FirebaseDatabase.getInstance()

        getIntentExtra()
        setListeners()
        initList()
    }

    private fun getIntentExtra() {
        from = intent.getStringExtra("from") ?: ""
        to = intent.getStringExtra("to") ?: ""
        date = intent.getStringExtra("date") ?: ""
    }

    private fun setListeners() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun initList() {
        val tripsRef: DatabaseReference = database.getReference("Trips")
        val list = ArrayList<Trip>()
        val query: Query = tripsRef.orderByChild("from").equalTo(from)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                list.clear()

                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val trip = item.getValue(Trip::class.java)
                        trip?.let {
                            if (it.to == to) {
                                list.add(it)
                            }
                        }
                    }
                }

                binding.searchView.layoutManager =
                    LinearLayoutManager(this@ResultActivity, LinearLayoutManager.VERTICAL, false)

                binding.searchView.adapter = TripsAdapter(list)

                binding.progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
