package com.example.sweet_store.payments

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.payment_method.PaymentResponse

class PaymentMethodHolder(cardLayout: View) : RecyclerView.ViewHolder(cardLayout) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun linkLayoutItems(currentItem: PaymentResponse) {
        var brands = mapOf<String, Int>(
            "Elo" to R.drawable.flag_elo,
            "American Express" to R.drawable.flag_american_express,
            "Visa" to R.drawable.flag_visa,
            "MasterCard" to R.drawable.flag_mastercard
        )

        val paymentName: TextView = itemView.findViewById(R.id.payment_name)
        val cardNumber: TextView = itemView.findViewById(R.id.card_number)
        val brandPayment: ImageView = itemView.findViewById(R.id.brand_payment)
        val expirationDate: TextView = itemView.findViewById(R.id.expiration_date)

        paymentName.text = currentItem.paymentName
        cardNumber.text = currentItem.cardNumber
        brandPayment.setImageResource(brands.getOrDefault(currentItem.brand, R.drawable.flag_mastercard))
        expirationDate.text = currentItem.expirationDate.toString()
    }
}