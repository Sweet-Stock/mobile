package com.example.sweet_store.service

import com.example.sweet_store.model.orders.OrderResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {
    @GET("order/get-user-orders/{uuidUser}")
    fun getAllOrders(@Path("uuidUser") uuidUser: String): Call<List<OrderResponse>>
}