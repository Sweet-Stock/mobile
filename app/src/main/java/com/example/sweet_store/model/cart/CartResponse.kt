package com.example.sweet_store.model.cart

data class CartResponse(
    val itens: List<ProductResponseAPI>? = mutableListOf(),
    val message: String ? = ""
)