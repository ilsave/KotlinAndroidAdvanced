package ru.ilsave.ThreoryBehind.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_quotes.*
import ru.ilsave.ThreoryBehind.R
import ru.ilsave.ThreoryBehind.data.Quote
import ru.ilsave.ThreoryBehind.utilities.InjectiorUtils
import java.lang.StringBuilder

class QuotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }

    private fun initializeUi(){
        val factory = InjectiorUtils.provideQuotesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer {
            quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach {quote ->
                stringBuilder.append("$quote\n\n ")
            }
            tv_quotes.text = stringBuilder.toString()
        })
        b_addQuote.setOnClickListener {
            val quote = Quote(et_quote.text.toString(), et_author.text.toString())
            viewModel.addQuote(quote)
            et_quote.setText("")
            et_author.setText("")
        }
    }
}