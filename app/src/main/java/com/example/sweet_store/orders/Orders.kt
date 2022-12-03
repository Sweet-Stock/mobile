package com.example.sweet_store.orders

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.ActivityErrorPage
import com.example.sweet_store.databinding.ActivityOrdersBinding
import com.example.sweet_store.model.orders.OrderResponse
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.OrderService
import com.example.sweet_store.ui.orders. OrdersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Orders : AppCompatActivity() {
    val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityOrdersBinding
    var orderResponseList: MutableList<OrderResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        val recyclerContainer = binding.orderRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(baseContext)

        val request =
            retrofit.create(OrderService::class.java)
                .getAllOrders( "568571f3-e07f-48e8-b891-c35510de98fe")

        request.enqueue(object : Callback<List<OrderResponse>> {
            override fun onResponse(call: Call<List<OrderResponse>>, response: Response<List<OrderResponse>>) {
                if (response.code() == 200) {
                    response.body()!!.forEach(orderResponseList::add)
                    recyclerContainer.adapter = OrdersAdapter(orderResponseList)
                }
            }

            override fun onFailure(call: Call<List<OrderResponse>>, t: Throwable) {
                val errorPage = Intent(this@Orders, ActivityErrorPage::class.java)
                errorPage.putExtra("error", "Falha ao listar pedidos. Motivo: ${t.message}")
                startActivity(errorPage)
            }
        })
    }
}