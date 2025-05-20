package com.example.trainingtesttask.data.models.DTOmodels

import com.example.trainingtesttask.data.models.Offer
import com.example.trainingtesttask.data.models.Vacancy

data class ApiResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)
