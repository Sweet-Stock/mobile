package com.example.sweet_store.service

import com.example.sweet_store.model.cart.CartResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartService {
    @GET("cart/get-user-cart-by-uuid/568571f3-e07f-48e8-b891-c35510de98fe")
    fun getAllCartItems(): Call<CartResponse>
}