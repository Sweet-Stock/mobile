package com.example.sweet_store.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.databinding.ActivityOrdersBinding

class Orders : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        val recyclerContainer = binding.orderRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)
        recyclerContainer.adapter = OrdersAdapter()
    }
}