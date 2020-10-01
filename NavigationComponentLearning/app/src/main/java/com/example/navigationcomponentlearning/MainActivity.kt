package com.example.navigationcomponentlearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//приложение использует 3 фрагмента, переключение между ними сделано с помощью navigation controller
// 1 фрагмент -> 2 фрагмент я использую класс Bundle, в который можно положить примитивные типы данных,
// если надо положить не примитив, то создаем класс и помечаем его как serialized и прописываем его
// в агрументах в схеме компонента
// 2 фрагмент -> 3 фрагмент я использую класс saveArgs, в котором могу доставать значения
// (если не примитив, то помечать надо custom parseble)
// чтобы выйти из приложения после нажатия кнопки назад - в схеме указываешь popupInclusive - true
// и как я понял backstack очищается и при 2-м нажатии кнопки назад ты выходишь из приложения


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_main)

    }
}