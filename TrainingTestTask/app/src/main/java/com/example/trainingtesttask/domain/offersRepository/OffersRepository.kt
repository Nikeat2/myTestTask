package com.example.trainingtesttask.domain.offersRepository

import com.example.trainingtesttask.data.models.Offer

interface OffersRepository {

    suspend fun getOffers() : List<Offer>

}