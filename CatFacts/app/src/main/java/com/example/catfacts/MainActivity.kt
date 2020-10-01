package com.example.catfacts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://cat-fact.herokuapp.com"

val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCurrentData()
    }

    fun onClickActivity(view: View) {
        getCurrentData()
    }

    private fun getCurrentData() {

        tvCatFact.visibility = View.INVISIBLE
        tvStamp.visibility = View.INVISIBLE
        progressBar.visibility= View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCatFacts().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.text)

                    withContext(Dispatchers.Main){
                        tvCatFact.visibility = View.VISIBLE
                        tvStamp.visibility = View.VISIBLE
                        progressBar.visibility= View.GONE


                        tvCatFact.text = data.text
                        tvStamp.text = data.createdAt
                    }
                }
            } catch(e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Seems something wrong", Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, e.toString())
            }
        }
    }

}

