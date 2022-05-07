package br.com.bankline_app_mobile.data

import android.util.Log
import androidx.lifecycle.liveData
import br.com.bankline_app_mobile.data.remote.BankLineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanklineRepository {
///SINGLETON - essa propriedade não vai ser instanciada mais de uma vez

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankLineApi::class.java)
        ///Minha RestaPI vai ser apartir da URL definida ali em cima,
        ///de acondo com a interface BanklineApi
    }

    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
            ///Emite um estado de sucesso assim que a API responder a consulta dela
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }
}
