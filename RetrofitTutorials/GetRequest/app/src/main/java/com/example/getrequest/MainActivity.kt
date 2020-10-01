package com.example.getrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getrequest.adapter.MyAdapter
import com.example.getrequest.model.Post
import com.example.getrequest.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
       // viewModel.getPost()

      //  val myPost = Post(2,2,"Hey there!", "I'm Ilsave!")
       // viewModel.pushPost(post = myPost)



        val options: HashMap<String, String> = HashMap()
        options.put("_sort", "id")
        options.put("_order", "desc")

      //  viewModel.getCustomPosts(2, "id", "desc")
        //   viewModel.pushPost2(2,2,"hey from Balachna, Russland", "Ilsave here!")
        viewModel.getPost()
        viewModel.myResponse1.observe(this, Observer { response ->
            if (response.isSuccessful){
               // response.body()?.let{ myAdapter.setData(it) }
                Log.d("Main", "response body = ${response.body().toString()}")
                Log.d("Main", "response code = ${response.code()}")
                Log.d("Main", "response message = ${response.headers()}")
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerView(){
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}