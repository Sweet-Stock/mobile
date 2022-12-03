package com.example.sweet_store.cart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityCartBinding
import com.example.sweet_store.model.cart.CartResponse
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
        loadCart()
    }

    private fun loadCart() {
        val recyclerContainer = binding.cartRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)

//        var userId: String? = getUserIdFromSharedPrefs()

        val request =
            retrofit.create(CartService::class.java)
                .getAllCartItems(uuidUser = "568571f3-e07f-48e8-b891-c35510de98fe")

        request.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.code() == 200) {
                    recyclerContainer.adapter = CartAdapter(response.body()?.itens!!)
                    Toast.makeText(baseContext, "Retornando itens do carrinho", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    "Falha ao buscar itens do carrinho",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}