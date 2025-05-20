package com.example.trainingtesttask.data.models.DTOmodels

import com.google.gson.annotations.SerializedName

data class Experience(
    @SerializedName("previewText")
    val previewText: String,

    @SerializedName("text")
    val text: String
)


