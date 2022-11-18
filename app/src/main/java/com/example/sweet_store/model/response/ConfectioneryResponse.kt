package com.example.sweet_store.model.response

import com.example.sweet_store.model.address.Address

data class ConfectioneryResponse(

    val uuid: String,
    val name: String,
    val fantasyName: String,
    val ceo: String,
    val cpf: String,
    val cnpj: String,
    val email: String,
    val picture: String,
    val telephoneNumber: String,
    val address: Address
)
