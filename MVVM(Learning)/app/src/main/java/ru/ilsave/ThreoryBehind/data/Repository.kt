package ru.ilsave.ThreoryBehind.data

class Repository private constructor(private val quoteDao : FakeQuoteDao) {

    fun addQuote(quote: Quote){
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(quoteDao: FakeQuoteDao) = instance ?: synchronized(this) {
            instance ?: Repository(quoteDao).also {
                instance = it
            }
        }

    }
}