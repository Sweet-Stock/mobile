package com.example.sweet_store.payments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.payment_method.Payment

class PaymentMethodAdapter(
    private var paymentMethodList: MutableList<Payment>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_card_payment_method, parent, false)
        return PaymentMethodHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = paymentMethodList[position]
        (holder as PaymentMethodHolder).linkLayoutItems(currentItem)
    }

    override fun getItemCount() = paymentMethodList.size
}