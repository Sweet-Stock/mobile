package com.example.sweet_store.service

import com.example.sweet_store.model.response.ProductVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Product {

    @GET("products")
    fun getProducts() : Call<List<ProductVO>>

    @GET("products/{uuid}")
    fun getProductById( @Path("uuid") id: String) : Call<ProductVO>

    @GET("products/all-products-no-sold-by-category/{id}")
    fun getCategoryProducts( @Path("id") id: String) : Call<List<ProductVO>>
}