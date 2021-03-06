package com.example.shoplistmvvm.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoplistmvvm.R
import com.example.shoplistmvvm.data.db.ShoppingDatabase
import com.example.shoplistmvvm.data.db.entities.ShoppingItem
import com.example.shoplistmvvm.data.repositories.ShoppingRepository
import com.example.shoplistmvvm.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
//
//        val database = ShoppingDatabase(this)
//
//        val repository = ShoppingRepository(database)
//
//        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter
        
        viewModel.getAllShoppingItems().observe(this, Observer { 
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        
        fab.setOnClickListener { 
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onAddButtonClick(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()


        }
    }
}