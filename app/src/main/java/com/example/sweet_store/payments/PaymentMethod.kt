package com.example.sweet_store.payments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityPaymentMethodBinding
import com.example.sweet_store.model.payment_method.PaymentResponse
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.PaymentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentMethod : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding
    val retrofit = Rest.getInstance()
    var paymentResponseMethodList: MutableList<PaymentResponse> = mutableListOf()


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

        val prefs = baseContext?.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)

        val uuid: String = prefs?.getString("userId", "") ?: ""
//        var userId: String? = getUserIdFromSharedPrefs()

        val request =
            retrofit.create(PaymentService::class.java)
                .getAllPaymentMethodsFromUser(uuid)

        request.enqueue(object : Callback<List<PaymentResponse>> {
            override fun onResponse(
                call: Call<List<PaymentResponse>>,
                response: Response<List<PaymentResponse>>
            ) {
                if (response.code() == 200) {
                    response.body()!!.forEach(paymentResponseMethodList::add)
                    recyclerContainer.adapter = PaymentMethodAdapter(paymentResponseMethodList)
                }
            }

            override fun onFailure(call: Call<List<PaymentResponse>>, t: Throwable) {
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

    fun goToAddCreditCardPage(view: View) {
        val addPaymentPage = Intent(this@PaymentMethod, AddCreditCard::class.java)
        startActivity(addPaymentPage)
    }
}