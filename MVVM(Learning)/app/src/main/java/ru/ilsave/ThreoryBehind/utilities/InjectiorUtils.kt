package ru.ilsave.ThreoryBehind.utilities

import ru.ilsave.ThreoryBehind.data.FakeDatabase
import ru.ilsave.ThreoryBehind.data.FakeQuoteDao
import ru.ilsave.ThreoryBehind.data.Repository
import ru.ilsave.ThreoryBehind.ui.quotes.QuotesViewModelFactory

object InjectiorUtils {

    fun provideQuotesViewModelFactory(): QuotesViewModelFactory{
        val quoteRepository = Repository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}