package com.example.sweet_store.service

import com.example.sweet_store.model.request.AddProductCart
import com.example.sweet_store.model.response.AddProductCartResponse
import com.example.sweet_store.model.response.ProductVO
import com.example.sweet_store.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Product {

    @GET("products/products-no-sold-by-uuid-company/{uuid}")
    fun getProducts(@Path("uuid") id: String): Call<List<ProductVO>>

    @GET("products/{uuid}")
    fun getProductById(@Path("uuid") id: String): Call<ProductVO>

    @GET("products/all-products-no-sold-by-category/{id}")
    fun getCategoryProducts(@Path("id") id: String): Call<List<ProductVO>>

    @POST("cart/add/{uuid}")
    fun addCart(@Body body: List<AddProductCart>, @Path("uuid") uuid: String): Call<AddProductCartResponse>
}