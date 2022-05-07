package br.com.bankline_app_mobile.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.bankline_app_mobile.R
import br.com.bankline_app_mobile.databinding.ActivityWelcomeBinding
import br.com.bankline_app_mobile.domain.Correntista
import br.com.bankline_app_mobile.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    ///Quando alguém a chamar, o código no escopo será executado
    private val binding by lazy{
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener{
            val accountHolderId = binding.etAccountHolderId.text.toString().toInt()
            val accountHolder = Correntista(accountHolderId)

            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
                ///Mandar o dado para a próxima activity através da constante
            }
            startActivity(intent)
        }
    }
}