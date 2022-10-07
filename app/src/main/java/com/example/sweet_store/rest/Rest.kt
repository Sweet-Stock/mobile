package com.example.sweet_store.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    //url base do emulador
    private val baseUrl = "http://3.221.231.115/api/v1/sweet-store/"

    //url base para celular
    //private val baseUrl = "http://3.221.231.115/api/v1/sweet-store/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}