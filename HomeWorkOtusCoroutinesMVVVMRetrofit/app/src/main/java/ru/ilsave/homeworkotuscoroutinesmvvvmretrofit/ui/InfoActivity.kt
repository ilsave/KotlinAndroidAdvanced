package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info.*
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.R

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        tv_info.text = intent.getStringExtra("no key discovered!")
        tv_type.text = intent.getStringExtra("no type discovered!")
        tv_accessibility.text = intent.getDoubleExtra("no accessibility discovered!", 0.0).toString()
    }
}