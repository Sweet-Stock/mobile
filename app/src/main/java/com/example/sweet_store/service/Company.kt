package com.example.sweet_store.service

import com.example.sweet_store.model.response.Category
import com.example.sweet_store.model.response.ConfectioneryResponse
import com.example.sweet_store.model.response.ViaCepResponse
import com.example.sweet_store.ui.confectionery.ConfectioneryVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Company {

    @GET("companies")
    fun getConfectionery(): Call<List<ConfectioneryVO>>

    @GET("companies/get-companies-by-category/{category}")
    fun getConfectioneryByCategory(@Path("category") category: String): Call<List<ConfectioneryVO>>

    @GET("companies/{id}")
    fun getConfectioneryById(@Path("id") id: String): Call<ConfectioneryVO>

}