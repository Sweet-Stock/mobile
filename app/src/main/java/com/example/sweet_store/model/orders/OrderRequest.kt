package com.example.sweet_store.model.orders

data class OrderRequest(
    val uuidUser: String,
    val idPayment: Long,
)