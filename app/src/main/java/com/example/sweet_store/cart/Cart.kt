package com.example.sweet_store.cart

import android.content.Intent
import android.os.Bundle
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
import android.content.SharedPreferences
import java.lang.String.format

class Cart : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var prefs: SharedPreferences
    private var name: String? = ""
    private var email: String? = ""
    private var uuid: String? = ""
    val retrofit = Rest.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request =
            retrofit.create(CartService::class.java)

        loadSharedPreferencesData()

        request.getAllCartItems(uuidUser = uuid?: "")
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

    private fun loadSharedPreferencesData() {
        prefs = this@Cart.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        name = prefs?.getString("userName", null) ?: ""
        email = prefs?.getString("userEmail", null) ?: ""
        uuid = prefs?.getString("userId", "") ?: ""
    }

    private fun setupNextStep(subtotal: Double, frete: Double, total: Double) {
        val nextButton = binding.cartNextButton
        nextButton.setOnClickListener {
            val intent = Intent(baseContext, CheckoutCart::class.java)
            intent.putExtra("subtotal", subtotal)
            intent.putExtra("frete", frete)
            intent.putExtra("total", total)
            startActivity(intent)
        }
    }

    fun loadTotal(products: MutableList<ProductResponseAPI>) {
        val tvSubtotal = binding.subtotalItemCart
        val tvFrete = binding.freteItemCart
        val tvTotal = binding.totalItemCart

        val subtotal = products.sumOf { it.saleValue!!.toDouble() * it.quantityInCart!! }
        var frete = (subtotal * 0.08)
        if(frete <= 9.99) frete = 9.99
        val total = subtotal + frete
        tvSubtotal.text = format("R$ %.2f", subtotal)
        tvFrete.text = format("R$ %.2f", frete)
        tvTotal.text = format("R$ %.2f", total)

        setupNextStep(subtotal, frete, total)
    }

    private fun loadCart(productResponseAPIS: MutableList<ProductResponseAPI>) {
        val recyclerContainer = binding.cartRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)
        recyclerContainer.adapter = CartAdapter(productResponseAPIS)
    }
}