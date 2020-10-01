package ru.ilsave.ThreoryBehind.ui.quotes

import androidx.lifecycle.ViewModel
import ru.ilsave.ThreoryBehind.data.Quote
import ru.ilsave.ThreoryBehind.data.Repository

class QuotesViewModel(private val quoteRepository: Repository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)



}