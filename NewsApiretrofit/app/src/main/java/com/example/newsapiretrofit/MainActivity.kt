package com.example.newsapiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiretrofit.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.system.measureTimeMillis


const val BASE_URL = "https://newsapi.org/"
class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val link = "https://api.currentsapi.services/v1/latest-news?language=us&apiKey=xm5boA-1lKq8kWgi8KiqJwAk4MdoVOTzc8RkjB0H1yaJ-J7K"

        makeAPIRequest()
    }

    private fun fadeInFromBlack(){
        v_blackScreen.animate().apply {
            alpha(0f)
                duration = 3000
        }.start()
    }

    private fun setUpRecyclerView(){
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList, linksList)
    }

    private fun addToList(title: String, description: String, image: String, link: String){
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
        linksList.add(link)
    }

    private fun makeAPIRequest(){

        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIRequests::class.java)

        Log.d("MainActivity", "Result = $api")


        GlobalScope.launch(Dispatchers.IO) {

            try {

                val response = api.getNews()

                for(article in response.articles){
                    Log.d("MainActivity", "Result = $article")
                    addToList(article.title, article.description, article.urlToImage, article.url)
                }

                withContext(Dispatchers.Main){
                   setUpRecyclerView()
                    fadeInFromBlack()
                    progressBar.visibility = View.GONE
                }

            } catch (e: Exception){
                Log.d("MainActivity", e.toString())

                withContext(Dispatchers.Main){
                    attemptRequestAgain()
                }
            }

        }
    }
    private fun attemptRequestAgain() {
        countDownTimer = object : CountDownTimer(5 * 1000, 1000){
            override fun onFinish() {
                makeAPIRequest()
                countDownTimer.cancel()
            }

            override fun onTick(p0: Long) {
                Log.i("MainActivity", "Could not load data... Trying again in ${p0/1000} seconds")
            }
        }
        countDownTimer.start()
    }

}