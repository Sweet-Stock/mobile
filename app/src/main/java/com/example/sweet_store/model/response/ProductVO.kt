package com.example.sweet_store.model.response

import java.time.LocalDate
import java.util.*

data class ProductVO(
    val uuid: String,
    val name: String,
    val saleValue: String,
    val expirationDate: Date,
    val dateInsert: LocalDate,
    val dateUpdate: LocalDate,
    val category: String,
)
