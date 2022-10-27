package com.example.sweet_store.service

import com.example.sweet_store.model.response.ViaCepResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Company {

    @GET("companies/")
    fun getConfectionery() : Call<ViaCepResponse>

}