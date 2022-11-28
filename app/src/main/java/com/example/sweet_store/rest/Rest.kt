package com.example.sweet_store.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    //url base do emulador
    private val baseUrlSweetStore = "https://sweetstore-backend.servehttp.com/api/v1/sweet-store/"
    private val baseUrlCep = "https://viacep.com.br/ws/"
    private val baseUrlSweetStock = "https://sweetstock-backend.servehttp.com/v1/sweet-stock/"
    //url base para celular
    //private val baseUrl = "http://3.221.231.115/api/v1/sweet-store/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlSweetStore).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getInstanceSweetStock(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlSweetStock).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getInstanceCep(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlCep).addConverterFactory(GsonConverterFactory.create()).build()
    }
}