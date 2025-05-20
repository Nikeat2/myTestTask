package com.example.trainingtesttask.data.retrofit

import android.content.Context
import android.util.Log
import com.example.trainingtesttask.R
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class MockInterceptor @Inject constructor (private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        try {
            val response = chain.proceed(originalRequest)
            if (response.isSuccessful) {
                return response
            }
        } catch (e: Exception) {
            Log.e("MockResponseInterceptor", "Request failed: ${e.message}")
        }

        val mockJsonFileName = "mock_vacancies.json"
        val mockJson = context.resources.openRawResource(R.raw.mock_vacancies).bufferedReader().use { it.readText() }

        return Response.Builder()
            .request(originalRequest)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("Mock Response")
            .body(mockJson.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }
}