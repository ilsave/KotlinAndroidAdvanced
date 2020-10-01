package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.other.Constans.Companion.BASE_URL

class RetrofitInstance {
    companion object{
        // lazy значит что мы инициализируем только один раз
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)    // задаем уровень (бади потому что хотим видеть основу ответа)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        //создание объекта для получения запроса
        val api by lazy {
            retrofit.create(infoDao::class.java)
        }
    }
}