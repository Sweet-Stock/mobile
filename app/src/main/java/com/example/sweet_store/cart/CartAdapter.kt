package com.example.sweet_store.cart

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.cart.ProductResponseAPI

class CartAdapter(private var productResponse: List<ProductResponseAPI>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_card_cart, parent, false)
        return CartHolder(cardLayout)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = productResponse[position]
        (holder as CartHolder).linkLayoutItems(currentItem)
    }

    override fun getItemCount() = productResponse.size

}
