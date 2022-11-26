package com.example.sweet_store.service

import com.example.sweet_store.model.payment_method.Payment
import retrofit2.Call
import retrofit2.http.GET

interface PaymentService {
    // TODO: colocar a o path correto
    @GET("")
    fun getAllPaymentMethodsFromUser(): Call<List<Payment>>
}