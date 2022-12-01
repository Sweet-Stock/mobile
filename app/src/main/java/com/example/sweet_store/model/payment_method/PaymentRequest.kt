package com.example.sweet_store.model.payment_method

data class PaymentRequest(
    val uuidUser: String,
    val cardNumber: String,
    val expirationDate: String,
    val cardHolderName: String,
    val cvcCode: String,
    val type: String,
    val brand: String,
    val paymentName: String,
)
