package com.example.trainingtesttask.data.models

import com.example.trainingtesttask.data.models.DTOmodels.Address
import com.example.trainingtesttask.data.models.DTOmodels.Experience
import com.example.trainingtesttask.data.models.DTOmodels.Salary
import com.google.gson.annotations.SerializedName

data class Vacancy (
    @SerializedName("id")
    val id: String,

    @SerializedName("lookingNumber")
    val lookingNumber: Int?,

    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: Address,

    @SerializedName("company")
    val company: String,

    @SerializedName("experience")
    val experience: Experience,

    @SerializedName("publishedDate")
    val publishedDate: String,

    @SerializedName("isFavorite")
    val isFavorite: Boolean,

    @SerializedName("salary")
    val salary: Salary,

    @SerializedName("schedules")
    val schedules: List<String>,

    @SerializedName("appliedNumber")
    val appliedNumber: Int?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("responsibilities")
    val responsibilities: String?,

    @SerializedName("questions")
    val questions: List<String>?
)
