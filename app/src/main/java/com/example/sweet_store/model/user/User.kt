package com.example.sweet_store.model.user

import com.example.sweet_store.model.address.Address
import sweet.apisweetstore.enums.AuthType
import sweet.apisweetstore.enums.ProfileType
import java.time.LocalDateTime
import java.util.*

data class UserRequest(
    val name: String,
    val email: String,
    val image: String?,
    val phone: String,
    val profile: String?,
    var password: String,
    val address: Address?,
)
