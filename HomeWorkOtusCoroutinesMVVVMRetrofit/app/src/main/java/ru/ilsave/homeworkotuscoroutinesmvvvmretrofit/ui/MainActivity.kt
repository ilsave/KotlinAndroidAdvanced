package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.R
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.adapter.RequestAdapter
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network.InfoAPI
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network.RetrofitInstance
import java.lang.StringBuilder

//приложение просто берет первые 5 названий с апи, потом показывает более детальную информацию

class MainActivity : AppCompatActivity() {

    lateinit var requestAdapter: RequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()


        val listApi = StringBuilder()

//        runBlocking {
//            for (i in 1..5) {
//                val response = RetrofitInstance.api.getInfo("$i")
//                listApi.append("${response.body()} \n \n")
//            }
//        }

        val listInfo : MutableList<InfoAPI> = ArrayList<InfoAPI>()
        runBlocking {
            for(i in 1..7){
                listInfo.add(RetrofitInstance.api.getInfo("$i"))
            }
        }

        //дифер для того чтоы лист не пересоздался (не вызывать notifydatachange)
        requestAdapter.differ.submitList(listInfo)


        requestAdapter.setOnItemClickListener {
            Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, InfoActivity::class.java).apply {
                putExtra("no key discovered!", it.key)
                putExtra("no type discovered!", it.type)
                putExtra("no accessibility discovered!", it.accessibility)

            }
            intent.putExtra("no key discovered!", it.key)
            Log.d("MainActivity", "key = ${it.key} \n type = ${it.type} \n accessibility = ${it.accessibility}")
            startActivity(intent)

        }
    }

    private fun setUpRecyclerView(){
        requestAdapter = RequestAdapter()
        rv_response.apply {
            adapter = requestAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}