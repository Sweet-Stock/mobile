package com.example.sweet_store.payments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.payment_method.Payment

class PaymentMethodHolder(cardLayout: View) : RecyclerView.ViewHolder(cardLayout) {
    fun linkLayoutItems(currentItem: Payment) {
        val paymentName: TextView = itemView.findViewById(R.id.payment_name)
        val cardNumber: TextView = itemView.findViewById(R.id.card_number)
        val brandPayment: ImageView = itemView.findViewById(R.id.brand_payment)
        val expirationDate: TextView = itemView.findViewById(R.id.expiration_date)

        paymentName.text = currentItem.paymentName
        cardNumber.text = currentItem.cardNumber
        brandPayment.setImageResource(R.drawable.flag_mastercard)
        expirationDate.text = currentItem.expirationDate.toString()
    }
}