package com.example.sweet_store.payments

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.sweet_store.R
import com.example.sweet_store.model.payment_method.PaymentResponse

class SpinnerCartCheckoutAdapter(
    private var context: Context,
    private var paymentResponseList: MutableList<PaymentResponse>,
) : BaseAdapter() {
    var brands = mapOf<String, Int>(
        "Elo" to R.drawable.flag_elo,
        "American Express" to R.drawable.flag_american_express,
        "Visa" to R.drawable.flag_visa,
        "MasterCard" to R.drawable.flag_mastercard
    )


    private var inflter: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return paymentResponseList.size
    }

    override fun getItem(i: Int): Any? {
        return paymentResponseList[i]
    }

    override fun getItemId(i: Int): Long {
        return paymentResponseList[i].idPayment!!
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val view = inflter.inflate(R.layout.spinner_card_cart, null)
        val icon = view.findViewById<View>(R.id.spinner_brand_image_checkout) as ImageView?
        val number = view.findViewById<View>(R.id.spinner_number_payment_checkout) as TextView?
        icon?.setImageResource(brands.getOrDefault(paymentResponseList[i].brand, R.drawable.flag_mastercard))
        number?.text = paymentResponseList[i].cardNumber
        return view
    }
}