package ru.ilsave.ThreoryBehind.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ilsave.ThreoryBehind.data.Repository

class QuotesViewModelFactory(private val quoteRepository: Repository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quoteRepository) as T
    }
}