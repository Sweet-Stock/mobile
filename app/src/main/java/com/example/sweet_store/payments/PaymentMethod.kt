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
    var paymentMethodList: MutableList<Payment> = mutableListOf()


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

//        var userId: String? = getUserIdFromSharedPrefs()

        val request =
            retrofit.create(PaymentService::class.java)
                .getAllPaymentMethodsFromUser( "568571f3-e07f-48e8-b891-c35510de98fe")

        request.enqueue(object : Callback<List<Payment>> {
            override fun onResponse(call: Call<List<Payment>>, response: Response<List<Payment>>) {
                if (response.code() == 200) {
                    response.body()!!.forEach(paymentMethodList::add)
                    Toast.makeText(baseContext, "Ce é brabo", Toast.LENGTH_SHORT).show()
                    recyclerContainer.adapter = PaymentMethodAdapter(paymentMethodList)
                }
            }

            override fun onFailure(call: Call<List<Payment>>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    "Falha ao buscar formas de pagamento",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun getUserIdFromSharedPrefs(): String? {
        val sharedPref = this@PaymentMethod.getPreferences(MODE_PRIVATE)
        val defaultValue = ""
        return sharedPref.getString("userId", defaultValue)
    }
}