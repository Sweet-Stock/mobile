package com.example.sweet_store.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R

class OrdersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_card_orders, parent, false)
        return OrderHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OrderHolder).linkLayoutItems()
    }

    override fun getItemCount() = 10

}
