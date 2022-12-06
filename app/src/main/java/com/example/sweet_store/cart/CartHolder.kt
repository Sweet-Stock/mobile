package com.example.sweet_store.cart

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.WebViewHelpActivity
import com.example.sweet_store.model.cart.ProductResponseAPI
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String.format


class CartHolder(cardLayout: View) : RecyclerView.ViewHolder(cardLayout) {
    val retrofit = Rest.getInstance()

    private lateinit var prefs: SharedPreferences
    private var name: String? = ""
    private var email: String? = ""
    private var uuidUser: String? = ""
    private var uuidProduct: String? = ""

    fun linkLayoutItems(currentItem: ProductResponseAPI) {
        val imageItemCart = itemView.findViewById<ImageView>(R.id.image_item_cart)
        val nameItemCart = itemView.findViewById<TextView>(R.id.name_item_cart)
        val descriptionItemCart = itemView.findViewById<TextView>(R.id.description_item_cart)
        val priceItemCart = itemView.findViewById<TextView>(R.id.price_item_cart)
        val amountItemCart = itemView.findViewById<TextView>(R.id.amount_item_cart)
        val sumAmountCartItem = itemView.findViewById<ImageView>(R.id.sum_amount_cart_item)
        val subtractAmountCartItem = itemView.findViewById<ImageView>(R.id.sub_amount_cart_item)

        uuidProduct = currentItem.uuid
        nameItemCart.text = currentItem.name
        descriptionItemCart.text = currentItem.category?.name
        priceItemCart.text = format("R$ %.2f", currentItem.saleValue)
        amountItemCart.text = currentItem.quantityInCart.toString()
        loadSharedPreferencesData()
        loadCartItemImage(currentItem, imageItemCart)

        setupSumAndSubtractItemCart(sumAmountCartItem, subtractAmountCartItem, amountItemCart)

    }

    private fun loadSharedPreferencesData() {
        prefs =
            itemView.context.getSharedPreferences("PREFERENCE_NAME", AppCompatActivity.MODE_PRIVATE)
        name = prefs?.getString("userName", null) ?: ""
        email = prefs?.getString("userEmail", null) ?: ""
        uuidUser = prefs?.getString("userId", "") ?: ""
    }

    private fun setupRemoveItemFromCart(newQuantity: Int) {
        val request =
            retrofit.create(CartService::class.java)
                .updateAmountCartAmountItem(
                    uuidUser = uuidUser!!,
                    uuidProduct = uuidProduct!!,
                    newQuantity = newQuantity
                )
                .enqueue(object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {
                        val cartIntent = Intent(itemView.context, Cart::class.java)
                        (itemView.context as Activity).finish()
                        itemView.context.startActivity(cartIntent)
                    }
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println("Motivo do erro: " + t.message)
                        Toast.makeText(
                            itemView.context,
                            "Erro ao atualizar item do carrinho",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

    }

    private fun setupSumAndSubtractItemCart(
        sumAmountCartItem: ImageView,
        subtractAmountCartItem: ImageView,
        amountItemCart: TextView
    ) {
        sumAmountCartItem.setOnClickListener(OnClickListener {
            var amount = amountItemCart.text.toString().toInt() + 1
            setupRemoveItemFromCart(amount)
            amountItemCart.text = amount.toString()
        })
        subtractAmountCartItem.setOnClickListener(OnClickListener {
            var amount = amountItemCart.text.toString().toInt() - 1
            setupRemoveItemFromCart(amount)
            amountItemCart.text = amount.toString()
        })
    }

    private fun loadCartItemImage(
        currentItem: ProductResponseAPI,
        imageItemCart: ImageView
    ) {
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