package com.example.sweet_store.ui.orders

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.ActivityErrorPage
import com.example.sweet_store.databinding.FragmentOrderBinding
import com.example.sweet_store.model.orders.OrderResponse
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.OrderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderFragment : Fragment() {

    val retrofit = Rest.getInstance()
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: SharedPreferences
    private var uuidUser: String? = ""
    var orderResponseList: MutableList<OrderResponse> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val orderViewModel =
            ViewModelProvider(this).get(OrderViewModel::class.java)

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadSharedPreferencesData()

        loadRecyclerView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadSharedPreferencesData() {
        prefs = context?.getSharedPreferences("PREFERENCE_NAME",
            AppCompatActivity.MODE_PRIVATE
        )!!
        uuidUser = prefs?.getString("userId", "") ?: ""
    }

    private fun loadRecyclerView() {
        val recyclerContainer = binding.orderRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(context)
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
                Toast.makeText(context, "Teste 2", Toast.LENGTH_SHORT).show()

                val errorPage = Intent(context, ActivityErrorPage::class.java)
                errorPage.putExtra("error", "Falha ao listar pedidos. Motivo: ${t.message}")
                startActivity(errorPage)
            }
        })
    }
}