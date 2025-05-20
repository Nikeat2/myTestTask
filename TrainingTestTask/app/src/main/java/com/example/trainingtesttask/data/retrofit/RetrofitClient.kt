package com.example.trainingtesttask.data.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

const val BASE_URL = "https:mock.url.ru"

class RetrofitClient @Inject constructor(val context: Context) {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(MockInterceptor(context))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}