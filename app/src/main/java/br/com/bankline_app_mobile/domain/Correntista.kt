package br.com.bankline_app_mobile.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Correntista(
    val id: Int
) : Parcelable
