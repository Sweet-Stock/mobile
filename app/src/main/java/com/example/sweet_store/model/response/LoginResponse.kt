package com.example.sweet_store.model.response

data class LoginResponse(
    val uuid: String? = null,
    val email: String,
    val name: String,
    val message: String
)
