package com.example.sweet_store.cart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.ActivityErrorPage
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityCartBinding
import com.example.sweet_store.model.cart.CartResponse
import com.example.sweet_store.model.payment_method.PaymentResponse
import com.example.sweet_store.payments.PaymentMethod
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cart : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    val retrofit = Rest.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request =
            retrofit.create(CartService::class.java)

        request.getAllCartItems()
            .enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@Cart, "sucesso", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    println("Motivo do erro: " + t.message)
                    Toast.makeText(this@Cart, "Erro ao listar itens do carrinho", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun loadCart() {
        val recyclerContainer = binding.cartRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)

//        var userId: String? = getUserIdFromSharedPrefs()

    }
}