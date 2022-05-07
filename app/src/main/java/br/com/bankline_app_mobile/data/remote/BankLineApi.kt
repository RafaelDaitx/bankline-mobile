package br.com.bankline_app_mobile.data.remote

import br.com.bankline_app_mobile.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BankLineApi {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountHolderId: Int): List<Movimentacao>
    ///Acesso a movimentacao de acordo com ID uisando retrofit
}