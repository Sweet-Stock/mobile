package com.example.sweet_store.ui.orders

import android.content.SharedPreferences
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.orders.OrderResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OrderHolder(cardLayout: View) : RecyclerView.ViewHolder(cardLayout) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun linkLayoutItems(currentItem: OrderResponse) {

        val amountOrder: TextView = itemView.findViewById(R.id.tv_amount_order)
        val dateDescriptionOrder: TextView = itemView.findViewById(R.id.tv_date_description_order)
        val orderValue: TextView = itemView.findViewById(R.id.tv_order_value)
        val nameConfectioneryOrder: TextView =
            itemView.findViewById(R.id.tv_order_name_confectionery)
        val brandOrder = itemView.findViewById<ImageView>(R.id.brand_credit_card_order)
        val finalCreditCard: TextView = itemView.findViewById(R.id.tv_credit_card_number_order)
        var brands = mapOf(
            "Elo" to R.drawable.flag_elo,
            "American Express" to R.drawable.flag_american_express,
            "Visa" to R.drawable.flag_visa,
            "MasterCard" to R.drawable.flag_mastercard
        )


        dateDescriptionOrder.text = formatDescriptionDate(currentItem.dateOrder ?: "")
        nameConfectioneryOrder.text = currentItem.nameConfectionery
        orderValue.text = String.format("R$ %.2f", currentItem.valueOrder)
        finalCreditCard.text = currentItem.card
        brandOrder.setImageResource(
            brands.getOrDefault(
                currentItem.brandCard,
                R.drawable.flag_mastercard
            )
        )
        amountOrder.text = currentItem.quantityItems.toString() + " itens"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDescriptionDate(dateOrder: String): String {
        val date = LocalDate.parse(dateOrder.take(10), DateTimeFormatter.ISO_DATE)
        val months = mapOf(
            1 to "Janeiro",
            2 to "Fevereiro",
            3 to "Março",
            4 to "Abril",
            5 to "Maio",
            6 to "Junho",
            7 to "Julho",
            8 to "Agosto",
            9 to "Setembro",
            10 to "Outubro",
            11 to "Novembro",
            12 to "Dezembro"
        )
        return "${date?.dayOfMonth} de ${months.get(date?.monthValue)}"
    }
}
