package com.example.trainingtesttask.data.repositories

import com.example.trainingtesttask.data.models.Vacancy
import com.example.trainingtesttask.data.retrofit.VacanciesAPI
import com.example.trainingtesttask.domain.offersRepository.VacanciesRepository
import javax.inject.Inject

class VacanciesRepositoryImpl @Inject constructor(private val api: VacanciesAPI) : VacanciesRepository {
    override suspend fun getVacancies(): List<Vacancy> {
        return api.getVacancies().vacancies
    }
}