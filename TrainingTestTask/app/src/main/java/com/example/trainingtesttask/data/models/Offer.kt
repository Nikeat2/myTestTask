package com.example.trainingtesttask.data.models

data class Offer(
    val id: String?,
    val title: String,
    val button: Button?,
    val link: String?
)

data class Button(
    val text: String?
)
