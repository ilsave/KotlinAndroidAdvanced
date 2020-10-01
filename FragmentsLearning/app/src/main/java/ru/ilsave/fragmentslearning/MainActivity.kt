package ru.ilsave.fragmentslearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()


        //штука, которая нужна чтобы в начале заменить пространство фрагментом
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, firstFragment)
            commit()
        }

        btn_fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, firstFragment) // для того чтобы когда нажимали кнопку назад приложение не закрывалось, а показывало прошлый фрагмент
                addToBackStack(null)
                commit()
            }
        }

        btn_fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, secondFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}