package com.example.sweet_store.payments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityPaymentMethodBinding
import com.example.sweet_store.model.payment_method.Payment
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.PaymentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentMethod : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding
    val retrofit = Rest.getInstance()
    lateinit var paymentMethodList: MutableList<Payment>


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

        val request = retrofit.create(PaymentService::class.java).getAllPaymentMethodsFromUser()

        request.enqueue(object : Callback<List<Payment>> {
            override fun onResponse(call: Call<List<Payment>>, response: Response<List<Payment>>) {
                if (response.code() == 200) {
                    response.body()!!.forEach(paymentMethodList::add)
                    recyclerContainer.adapter = PaymentMethodAdapter(paymentMethodList)
                }
            }

            override fun onFailure(call: Call<List<Payment>>, t: Throwable) {
                Toast.makeText(baseContext, "Falha ao buscar formas de pagamento", Toast.LENGTH_SHORT).show()
            }
        })
    }
}