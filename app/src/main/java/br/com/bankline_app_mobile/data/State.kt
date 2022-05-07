package br.com.bankline_app_mobile.data

sealed class State<out T> {
    data class Success<out R>(val data: R? = null) : State<R?>()
    ///Parametro genérico(response) "R" é opicional

    data class Error(val message: String? = null): State<Nothing>()
    object Wait : State<Nothing>()
}
