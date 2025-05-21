package com.example.trainingtesttask.domain.offersRepository

import com.example.trainingtesttask.data.models.Vacancy

interface VacanciesRepository {

   suspend fun getVacancies() : List<Vacancy>

}