package com.example.edvoraapps.adaptclass

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edvoraapps.R
import com.example.edvoraapps.data.RidesDataItem

class RidesAdapter(): RecyclerView.Adapter<RidesAdapter.RidesViewHolder>() {
    var listOfRides: ArrayList<RidesDataItem> =  arrayListOf()

    inner class RidesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mapImageIv = view.findViewById<ImageView>(R.id.mapImage)
        val cityNameTv = view.findViewById<TextView>(R.id.cityName)
        val stateNameTv = view.findViewById<TextView>(R.id.stateName)
        val stationPathTv = view.findViewById<TextView>(R.id.stationPath)
        val dateTv = view.findViewById<TextView>(R.id.date)
        val distanceTv = view.findViewById<TextView>(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RidesViewHolder {
        return RidesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ride_detail_view_holder, parent, false))
    }

    override fun onBindViewHolder(holder: RidesViewHolder, position: Int) {
        with(listOfRides[position]) {
            with(holder) {
//                mapImageIv.setImageResource(position)
                cityNameTv.text = city
                stateNameTv.text = state
                stationPathTv.text = station_path.toString()
                dateTv.text = date
                //distanceTv.text = distance.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfRides.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newRideList: ArrayList<RidesDataItem>) {
        listOfRides = newRideList
        notifyDataSetChanged()
    }
}