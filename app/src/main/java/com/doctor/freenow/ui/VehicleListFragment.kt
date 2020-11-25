package com.doctor.freenow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doctor.freenow.R
import com.doctor.freenow.model.PoiList
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class VehicleListFragment : Fragment() {

    private lateinit var adapter: VehicleApiAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        setupUI()
        return view
    }

    private fun setupUI() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter =
                VehicleApiAdapter(
                        arrayListOf()
                )
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    private fun renderList(vehicles: List<PoiList>) {
        adapter.addData(vehicles)
        adapter.notifyDataSetChanged()
    }
}