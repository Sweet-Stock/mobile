package com.example.sweet_store.model.cart

import sweet.apisweetstore.integration.ProductResponseAPI

data class CartResponse(
    val itens: List<ProductResponseAPI>? = mutableListOf(),
    val message: String ? = ""
)