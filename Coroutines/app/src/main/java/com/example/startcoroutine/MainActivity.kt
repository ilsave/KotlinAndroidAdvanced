package com.example.startcoroutine

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        //живет столько же сколько и само приложение
//        //main cor for ui
//        //Io for db operations
//        //defaultf for long calc opation, breaks the main thread
//        GlobalScope.launch(Dispatchers.IO) {
//            val answer = doNetworkCall()
//            Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
//            withContext(Dispatchers.Main){
//                Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
//                tv_networkCall.text = answer
//            }
//        }
//
//
//        //runs on main thread and block ui
//        //use it for call suspend function
//        runBlocking {
//
//        }


//        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5){
//                Log.d(TAG, "coroutine is running")
//                delay(1000L)
//            }
//        }
//
//        runBlocking {
//            delay(2000L)
//           // job.join() //waiting for the end of repeat loop
//            job.cancel() // cancel the job
//            Log.d(TAG, "Main Thread is continuing")
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val answer1 = async { doNetworkCall1() } //создает новый поток
//                val answer2 = async { doNetworkCall2() } //это все создается асихронно
//                Log.d(TAG, "answer1 is ${answer1.await()}") //await ждет пока значение не
//                Log.d(TAG, "answer2 is ${answer2.await()}") //станет доступным
//            }
//                Log.d(TAG, "time is $time ms.")
//        }

//globalscope - корутины живут столько же, сколько и само приложение
        btnStartActivity.setOnClickListener {
            GlobalScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "still running")
                }
            }
//lifecycle - живет столько же, сколько и само активити
            btnStartActivity.setOnClickListener {
                lifecycleScope.launch {
                    while (true) {
                        delay(1000L)
                        Log.d(TAG, "still running")
                    }
                }

                GlobalScope.launch {
                    delay(5000L)
                    Intent(this@MainActivity, SecondActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }

        }

        suspend fun doNetworkCall(): String {
            delay(3000L)
            return "we made a networkCall inside"
        }

        suspend fun doNetworkCall1(): String {
            delay(3000L)
            return "we made a networkCall inside1"
        }

        suspend fun doNetworkCall2(): String {
            delay(3000L)
            return "we made a networkCall inside2"
        }
    }
}