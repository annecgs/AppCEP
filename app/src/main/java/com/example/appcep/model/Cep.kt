package com.example.appcep.model

import com.google.gson.annotations.SerializedName

data class Cep(
    @SerializedName("cep")
    val cep: String?,
    @SerializedName("logradouro")
    val logradouro: String?,
    @SerializedName("bairro")
    val bairro: String?,
    @SerializedName("localidade")
    val localidade: String?,
    @SerializedName("uf")
    val uf: String?,
    @SerializedName("ddd")
    val ddd: String?,
    @SerializedName("erro")
    val erro: String?
)