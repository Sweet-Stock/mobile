package com.example.sweet_store.model.user

import com.example.sweet_store.model.address.Address
import sweet.apisweetstore.enums.AuthType
import sweet.apisweetstore.enums.ProfileType
import java.time.LocalDateTime
import java.util.*

data class User(

    val uuid: String? = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val image: String?,
    val phone: String,
    val profile: ProfileType?,
    var password: String,
    val authType: AuthType?,
    val address: Address?,
)
