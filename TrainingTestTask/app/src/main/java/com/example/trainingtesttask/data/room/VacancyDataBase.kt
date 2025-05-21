package com.example.trainingtesttask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainingtesttask.data.models.Vacancy

@Database(entities = [Vacancy::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class, AddressConverter::class)
abstract class VacancyDataBase : RoomDatabase() {
    abstract fun getVacancyDao(): VacancyDao
}