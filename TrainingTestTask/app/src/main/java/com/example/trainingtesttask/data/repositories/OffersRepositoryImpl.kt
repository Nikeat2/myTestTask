package com.example.trainingtesttask.data.repositories

import com.example.trainingtesttask.data.models.Offer
import com.example.trainingtesttask.data.retrofit.VacanciesAPI
import com.example.trainingtesttask.domain.offersRepository.OffersRepository

class OffersRepositoryImpl(private val api: VacanciesAPI) : OffersRepository {
    override suspend fun getOffers(): List<Offer> {
        return api.getVacancies().offers
    }
}