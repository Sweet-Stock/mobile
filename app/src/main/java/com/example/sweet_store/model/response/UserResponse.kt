package com.example.sweet_store.model.response

import sweet.apisweetstore.enums.AuthType
import com.example.sweet_store.enums.ProfileType

data class UserResponse(
    val uuid: String,
    val name: String,
    val email: String,
    val profile: ProfileType? = ProfileType.MODERATE,
    val authType: AuthType? = AuthType.DEFAULT
)