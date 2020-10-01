package com.example.shoplistmvvm.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoplistmvvm.data.db.entities.ShoppingItem
import com.example.shoplistmvvm.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel (
    private val repository: ShoppingRepository
) : ViewModel() {


    //запуск потока в мейн thread (котлин корутины)
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    //im not using coro beacause its only read operation
    fun getAllShoppingItems() = repository.getAllShoppingItems()

}