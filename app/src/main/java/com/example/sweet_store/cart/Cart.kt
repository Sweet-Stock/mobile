package com.example.sweet_store.cart

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityCartBinding
import com.example.sweet_store.model.cart.CartResponse
import com.example.sweet_store.model.cart.ProductResponseAPI
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String.format

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
                        loadCart(response.body()!!.itens!!.toMutableList())
                        loadTotal(response.body()!!.itens!!.toMutableList())
                    }
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    println("Motivo do erro: " + t.message)
                    Toast.makeText(
                        this@Cart,
                        "Erro ao listar itens do carrinho",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun loadTotal(products: MutableList<ProductResponseAPI>) {
        val tvSubtotal = findViewById<TextView>(R.id.subtotal_item_cart)
        val tvFrete = findViewById<TextView>(R.id.frete_item_cart)
        val tvTotal = findViewById<TextView>(R.id.total_item_cart)

        val subtotal = products.sumOf { it.saleValue!!.toDouble() * it.total!! }
        tvSubtotal.text = format("R$ %.2f", subtotal)
        val frete = (subtotal * 0.08)
        tvFrete.text = format("R$ %.2f", frete)
        tvTotal.text = format("R$ %.2f", subtotal + frete)

    }

    private fun loadCart(productResponseAPIS: MutableList<ProductResponseAPI>) {
        val recyclerContainer = binding.cartRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)
        recyclerContainer.adapter = CartAdapter(productResponseAPIS)
    }
}