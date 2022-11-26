package com.example.sweet_store.service

import com.example.sweet_store.model.request.LoginRequest
import com.example.sweet_store.model.response.LoginResponse
import com.example.sweet_store.model.response.UserResponse
import com.example.sweet_store.model.response.ViaCepResponse
import com.example.sweet_store.model.user.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface User {

    @POST("user/login")
    fun login( @Body body: LoginRequest): Call<LoginResponse>

    @POST("user/register")
    fun register( @Body body: UserRequest): Call<UserResponse>

    @GET("{cep}/json/")
    fun getCep(@Path("cep") cep :String):Call<ViaCepResponse>
}