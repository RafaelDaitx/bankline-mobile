package br.com.bankline_app_mobile.ui.statement

import androidx.lifecycle.ViewModel
import br.com.bankline_app_mobile.data.BanklineRepository

class BankStatementViewModel : ViewModel() {

    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)
}