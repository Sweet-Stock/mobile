package com.example.sweet_store.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.response.ConfectioneryResponse

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_confectionery, parent, false)
        return HomeHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeHolder).linkLayoutItems()
    }

    override fun getItemCount() = 10

}

//private val list: List<ConfectioneryResponse>, private val onClick: (confectionery: ConfectioneryResponse) -> Unit