package com.example.edvoraapps.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edvoraapps.R
import com.example.edvoraapps.adaptclass.RidesAdapter
import com.example.edvoraapps.data.RidesDataItem
import com.example.edvoraapps.util.ApiCallNetworkResource
import com.example.edvoraapps.viewmodel.RideViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var ridesAdapter: RidesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var response:List<RidesDataItem>
    private val viewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        // initialise the recyclerview
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        ridesAdapter = RidesAdapter()
        recyclerView.adapter = ridesAdapter


        observeApiCall()
        viewModel.getAllRide()
    }



    // to observe the Api call
    @SuppressLint("NotifyDataSetChanged")
    private fun observeApiCall() {
        viewModel.rideResponseLiveData.observe(this) {
            Log.d("MQ", "$it:error message")
            when (it) {
                is ApiCallNetworkResource.Success -> {
                    Log.d("MQ", "$it:error messages")
//
                    if (it == null) {
                        Toast.makeText(this, "no result found", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        response = it.data!!
                        ridesAdapter.listOfRides = response.toMutableList() as ArrayList<RidesDataItem>
                        ridesAdapter.notifyDataSetChanged()
                    }
                }

                is ApiCallNetworkResource.Error -> {
                    Log.d("MQ", "$it:error messages try again")
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//
                }

                is ApiCallNetworkResource.Loading -> {
                    Log.d("MQ", "$it:error messages try more")
                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}