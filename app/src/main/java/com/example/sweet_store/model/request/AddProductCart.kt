package com.example.sweet_store.model.request

data class AddProductCart(
    val uuidProduct: String,
    val uuidCompany: String,
    val quantityProduct: Int,
)
