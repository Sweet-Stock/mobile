package com.example.sweet_store.model.user

import com.example.sweet_store.model.address.Address

data class UserRequest(
    val name: String,
    val email: String,
    val image: String?,
    val phone: String,
    val profile: String?,
    var password: String,
    val address: Address?,
)
