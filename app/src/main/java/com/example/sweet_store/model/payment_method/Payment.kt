package com.example.sweet_store.model.payment_method

import java.time.LocalDate

data class Payment(
    var idPayment: Long? = null,
    var uuidUser: String? = "",
    var cardNumber: String? = "",
    var expirationDate: LocalDate? = null,
    var cardHolderName: String? = "",
    var cvcCode: String? = "",
    var type: String = "",
    var brand: String = "",
    var paymentName: String = "",
)

