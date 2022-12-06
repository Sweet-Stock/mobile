package com.example.sweet_store.service

import com.example.sweet_store.model.cart.CartResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface CartService {
    @GET("cart/get-user-cart-by-uuid/{uuidUser}")
    fun getAllCartItems(@Path("uuidUser") uuidUser: String): Call<CartResponse>

    @PATCH("cart/update-quantity-item/{uuidUser}/{uuidProduct}/{newQuantity}")
    fun updateAmountCartAmountItem(
        @Path("uuidUser") uuidUser: String,
        @Path("uuidProduct") uuidProduct: String,
        @Path("newQuantity") newQuantity: Int,): Call<Void>
}