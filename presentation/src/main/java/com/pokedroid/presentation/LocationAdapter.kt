package com.pokedroid.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pokedroid.domain.model.Location

class LocationAdapter(private var locationList: List<Location>): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): LocationAdapter.LocationViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.location_list_item, parent, false) as TextView

        return LocationAdapter.LocationViewHolder(textView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.textView.text = locationList[position].name
    }

    override fun getItemCount() = locationList.size

}