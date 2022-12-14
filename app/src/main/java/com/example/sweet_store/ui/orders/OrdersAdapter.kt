package com.example.sweet_store.ui.orders

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.orders.OrderResponse

class OrdersAdapter(
    private var ordersResponseList: MutableList<OrderResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_card_orders, parent, false)
        return OrderHolder(cardLayout)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = ordersResponseList[position]
        (holder as OrderHolder).linkLayoutItems(currentItem)
    }

    override fun getItemCount() = ordersResponseList.size

}
