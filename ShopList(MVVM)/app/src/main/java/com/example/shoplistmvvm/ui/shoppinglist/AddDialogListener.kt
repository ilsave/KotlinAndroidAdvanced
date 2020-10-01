package com.example.shoplistmvvm.ui.shoppinglist

import com.example.shoplistmvvm.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClick(item: ShoppingItem)

}