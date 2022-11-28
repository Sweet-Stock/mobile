package com.example.sweet_store.model.response

import java.time.LocalDate
import java.util.*

data class ProductVO(
    val uuid: String,
    val name: String,
    val picture: String,
    val saleValue: String,
    val expirationDate: String,
    val dateInsert: String,
    val dateUpdate: String,
 //   val category: String,
)
