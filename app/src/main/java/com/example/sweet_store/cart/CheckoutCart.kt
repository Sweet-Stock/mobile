package com.example.sweet_store.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityCheckoutCartBinding

class CheckoutCart : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_cart)
        binding = ActivityCheckoutCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subtotal: Double = intent.getDoubleExtra("subtotal", 0.0)
        val frete: Double = intent.getDoubleExtra("frete", 0.0)
        val total: Double = intent.getDoubleExtra("total", 0.0)

    }
}