package com.example.sweet_store.ui.confectionery

import com.example.sweet_store.model.address.Address

data class ConfectioneryVO(
    val uuid: String,
    val name: String,
    val fantasyName: String,
    val ceo: String,
    val cpf: String,
    val cnpj: String,
    val picture: String,
    val email: String,
    val telephoneNumber: String,
    val address: Address,
    val isOpen: Boolean,
)
