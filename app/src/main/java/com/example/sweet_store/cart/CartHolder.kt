package com.example.sweet_store.cart

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.cart.ProductResponseAPI
import java.lang.String.format

class CartHolder(cardLayout: View) : RecyclerView.ViewHolder(cardLayout) {
    fun linkLayoutItems(currentItem: ProductResponseAPI) {
        val imageItemCart = itemView.findViewById<ImageView>(R.id.image_item_cart)
        val nameItemCart = itemView.findViewById<TextView>(R.id.name_item_cart)
        val descriptionItemCart = itemView.findViewById<TextView>(R.id.description_item_cart)
        val priceItemCart = itemView.findViewById<TextView>(R.id.price_item_cart)
        val amountItemCart = itemView.findViewById<TextView>(R.id.amount_item_cart)

        nameItemCart.text = currentItem.name
        descriptionItemCart.text = currentItem.category?.name
        priceItemCart.text = format("R$ %.2f", currentItem.saleValue)
        amountItemCart.text = currentItem.total.toString()

        if (!currentItem.picture.isNullOrEmpty()) {
            val bruteBase64 = currentItem.picture

            val base64 = formatBase64(bruteBase64)
            imageItemCart.setImageBitmap(convertStringToBitmap(base64))
        }
    }

    private fun formatBase64(code: String): String {
        var formattedString = code

        formattedString = formattedString.replace("data:image/png;base64,", "")
        formattedString = formattedString.replace("data:image/svg;base64,", "")
        formattedString = formattedString.replace("data:image/svg+xml;base64,", "")
        formattedString = formattedString.replace("data:image/jpeg;base64,", "")
        formattedString = formattedString.replace("data:image/jpg;base64,", "")
        return formattedString
    }

    private fun convertStringToBitmap(base64Str: String?): Bitmap? {
        val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
}