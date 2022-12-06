package com.example.sweet_store.service

import com.example.sweet_store.model.orders.OrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.sweet_store.model.orders.OrderRequest

interface OrderService {
    @GET("order/get-user-orders/{uuidUser}")
    fun getAllOrders(@Path("uuidUser") uuidUser: String): Call<List<OrderResponse>>

    @POST("order/")
    fun saveOrder(@Body orderRequest: OrderRequest): Call<OrderResponse>
}