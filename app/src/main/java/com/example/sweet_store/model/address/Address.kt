package com.example.sweet_store.model.address

import java.time.LocalDateTime
import java.util.*

data class Address(
    var city: String? = "",
    var complement: String? = "",
    var neighborhood: String? = "",
    var number: String? = "",
    var state: String? = "",
    var street: String? = "",
    var cep: String? = ""
)
