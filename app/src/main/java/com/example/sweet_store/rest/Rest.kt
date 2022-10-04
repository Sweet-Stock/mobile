package com.example.sweet_store.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    //url base do emulador
    private val baseUrl = "http://10.0.2.2:3000/"

    //url base para celular
    //private val baseUrl = "http://IP_DA_MAQUINA:3000/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}