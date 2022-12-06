package com.example.sweet_store.cart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.HomeActivity
import com.example.sweet_store.MainActivity
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityCheckoutCartBinding
import com.example.sweet_store.model.orders.OrderRequest
import com.example.sweet_store.model.orders.OrderResponse
import com.example.sweet_store.model.payment_method.PaymentResponse
import com.example.sweet_store.payments.PaymentMethod
import com.example.sweet_store.payments.SpinnerCartCheckoutAdapter
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.OrderService
import com.example.sweet_store.service.PaymentService
import com.example.sweet_store.ui.orders.OrderFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String.format

class CheckoutCart : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutCartBinding
    val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences
    private var name: String? = ""
    private var email: String? = ""
    private var uuidUser: String? = ""
    private var paymentResponseList = mutableListOf<PaymentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_cart)
        binding = ActivityCheckoutCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSharedPreferencesData()
        setTotalValues()
        getAllUserPaymentMethod()
    }

    private fun setTotalValues() {
        val subtotal: Double = intent.getDoubleExtra("subtotal", 0.0)
        val frete: Double = intent.getDoubleExtra("frete", 0.0)
        val total: Double = intent.getDoubleExtra("total", 0.0)

        binding.totalCheckoutCart.text = format("R$ %.2f", total)
        binding.freteCheckoutCart.text = format("R$ %.2f", frete)
        binding.subtotalCheckoutCart.text = format("R$ %.2f", subtotal)
    }

    private fun loadSharedPreferencesData() {
        prefs = this@CheckoutCart.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        name = prefs?.getString("userName", null) ?: ""
        email = prefs?.getString("userEmail", null) ?: ""
        uuidUser = prefs?.getString("userId", "") ?: ""
    }

    private fun getAllUserPaymentMethod() {
        val request =
            retrofit.create(PaymentService::class.java)
                .getAllPaymentMethodsFromUser(uuidUser ?: "")

        request.enqueue(object : Callback<List<PaymentResponse>> {
            override fun onResponse(
                call: Call<List<PaymentResponse>>,
                response: Response<List<PaymentResponse>>
            ) {
                if (response.code() == 200) {
                    paymentResponseList = response.body()?.toMutableList() ?: mutableListOf()
                    setupSpinnerPaymentMethod(spinnerPaymentMethod = binding.cartCheckoutPaymentMethod)
                    
                    setupBuyFinish()
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

    private fun setupBuyFinish() {
        binding.btnFinishCheckoutCart.setOnClickListener(View.OnClickListener {
            var idPayment = binding.cartCheckoutPaymentMethod.selectedItemId
            retrofit.create(OrderService::class.java)
                .saveOrder(OrderRequest(uuidUser = uuidUser ?: "", idPayment = idPayment))
                .enqueue(object : Callback<OrderResponse> {
                    override fun onResponse(
                        call: Call<OrderResponse>,
                        response: Response<OrderResponse>
                    ) {
                        val orders = Intent(this@CheckoutCart, MainActivity::class.java)
                        startActivity(orders)
                    }

                    override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                        println("Falha no pagamento. Motivo: ${t.message}")
                        Toast.makeText(this@CheckoutCart, "Falha no pagamento. Motivo: ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
        })
    }

    private fun setupSpinnerPaymentMethod(spinnerPaymentMethod: Spinner) {
        spinnerPaymentMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val customAdapter = SpinnerCartCheckoutAdapter(applicationContext, paymentResponseList)
        spinnerPaymentMethod.adapter = customAdapter
    }
}