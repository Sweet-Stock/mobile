package com.example.sweet_store.service

import com.example.sweet_store.model.payment_method.PaymentRequest
import com.example.sweet_store.model.payment_method.PaymentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentService {
    @GET("payment/{uuidUser}")
    fun getAllPaymentMethodsFromUser(@Path("uuidUser") uuidUser: String): Call<List<PaymentResponse>>

    @POST("payment/")
    fun addPayment(@Body paymentResponse: PaymentRequest): Call<PaymentResponse>
}