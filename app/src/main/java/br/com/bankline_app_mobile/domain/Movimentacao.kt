package br.com.bankline_app_mobile.domain

import com.google.gson.annotations.SerializedName

data class Movimentacao(
    val id: Int,
    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipo: TipoMovimentacao,
    @SerializedName("idConta")
    val idCorrentista: Int
)
