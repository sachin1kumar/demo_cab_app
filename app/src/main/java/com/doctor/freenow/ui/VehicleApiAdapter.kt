package com.doctor.freenow.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doctor.freenow.R
import com.doctor.freenow.model.PoiList
import kotlinx.android.synthetic.main.item_layout.view.*

class VehicleApiAdapter(private val vehicles: ArrayList<PoiList>) : RecyclerView.Adapter<VehicleApiAdapter.RecViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return RecViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        val vehicles = vehicles[position]
        holder.bind(vehicles)
        holder.itemView.setOnClickListener {
            //TODO
        }
    }

    override fun getItemCount(): Int = vehicles.size

    inner class RecViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(vehicles: PoiList) {
            itemView.tv_vehicle_id.text = vehicles.id
            itemView.tv_fleet_type.text = vehicles.fleetType
        }

    }

    fun addData(list: List<PoiList>) {
        vehicles.addAll(list)
    }

}