package com.example.sweet_store.model.payment_method

data class PaymentResponse(
    var idPayment: Long? = null,
    var uuidUser: String? = "",
    var cardNumber: String? = "",
    var expirationDate: String? = null,
    var cardHolderName: String? = "",
    var cvcCode: String? = "",
    var type: String = "",
    var brand: String = "",
    var paymentName: String = "",
)

