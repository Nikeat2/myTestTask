package com.example.trainingtesttask.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainingtesttask.data.models.Vacancy

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: Vacancy)

    @Query("SELECT * FROM FAVORITE_VACANCIES")
    suspend fun getVacancies(): List<Vacancy>
}