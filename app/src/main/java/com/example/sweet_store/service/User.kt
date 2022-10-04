package com.example.sweet_store.service

import com.example.sweet_store.model.request.LoginRequest
import com.example.sweet_store.model.response.LoginResponse
import com.example.sweet_store.model.response.UserResponse
import com.example.sweet_store.model.user.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface User {

    @POST("/login")
    fun login( @Body body: LoginRequest): Call<LoginResponse>

    @POST("/register")
    fun register( @Body body: UserRequest): Call<UserResponse>

}