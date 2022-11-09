package com.example.sweet_store.payments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityOrdersBinding
import com.example.sweet_store.databinding.ActivityPaymentMethodBinding

class PaymentMethod : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        val recyclerContainer = binding.paymentMethodRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)
        recyclerContainer.adapter = PaymentMethodAdapter()
    }
}