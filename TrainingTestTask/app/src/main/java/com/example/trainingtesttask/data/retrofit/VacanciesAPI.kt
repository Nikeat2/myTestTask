package com.example.trainingtesttask.data.retrofit

import com.example.trainingtesttask.data.models.DTOmodels.ApiResponse
import com.example.trainingtesttask.data.models.Offer
import retrofit2.http.GET

interface VacanciesAPI {

    @GET("vacancies")
    suspend fun getVacancies(): ApiResponse

}