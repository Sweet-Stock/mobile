package com.example.sweet_store.model.address

import java.time.LocalDateTime
import java.util.*

data class Address(
    val uuid: String? = UUID.randomUUID().toString(),
    val city: String,
    val complement: String,
    val neighborhood: String,
    val number: String,
    val state: String,
    val street: String,
    val cep: String
)
