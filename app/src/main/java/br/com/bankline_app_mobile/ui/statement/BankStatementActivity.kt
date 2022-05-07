package br.com.bankline_app_mobile.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bankline_app_mobile.R
import br.com.bankline_app_mobile.data.State
import br.com.bankline_app_mobile.databinding.ActivityBankStatementBinding
import br.com.bankline_app_mobile.databinding.ActivityWelcomeBinding
import br.com.bankline_app_mobile.domain.Correntista
import br.com.bankline_app_mobile.domain.Movimentacao
import br.com.bankline_app_mobile.domain.TipoMovimentacao
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER =
            "br.com.bankline_app_mobile.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    ///Quando alguém a chamar, o código no escopo será executado
    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER)
            ?: throw IllegalArgumentException()
        ///Recuperando o que é passado da WelcomeActitivity, e se não vier nada lança um throw
    }

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
        //Fazer o refresh da página de listagem das transações, ele recarrega a função findBankStatement
    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when (state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    ///Se tiver dado, ele vai passar
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                    ///Mostrar erro caso haver
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> binding.srlBankStatement.isRefreshing = true
            }
        }
        /*
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1,"03/05;2022 09:24:55", "EXEMPLO",1000.0,TipoMovimentacao.RECEITA,  1))
        dataSet.add(Movimentacao(1,"03/05;2022 09:24:55", "EXEMPLO",1000.0,TipoMovimentacao.DESPESA,  1))
        dataSet.add(Movimentacao(1,"03/05;2022 09:24:55", "EXEMPLO",1000.0,TipoMovimentacao.RECEITA,  1))
        dataSet.add(Movimentacao(1,"03/05;2022 09:24:55", "EXEMPLO",1000.0,TipoMovimentacao.RECEITA,  1))
        dataSet.add(Movimentacao(1,"03/05;2022 09:24:55", "EXEMPLO",1000.0,TipoMovimentacao.DESPESA,  1))
        ///MOCK DE TESTE
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)*/
    }
}