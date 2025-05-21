package com.example.trainingtesttask.presentation.fragments.vacanciesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trainingtesttask.data.models.Offer
import com.example.trainingtesttask.data.models.Vacancy
import com.example.trainingtesttask.data.room.VacancyDataBase
import com.example.trainingtesttask.di.MyApp
import com.example.trainingtesttask.domain.offersRepository.OffersRepository
import com.example.trainingtesttask.domain.offersRepository.VacanciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VacanciesViewModel @Inject constructor(
    offersRepository: OffersRepository,
    vacanciesRepository: VacanciesRepository,
    vacancyDataBase: VacancyDataBase
) : ViewModel() {

    private var _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()

    private var _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies = _vacancies.asStateFlow()

    init {
        viewModelScope.launch {
            val newOffers = async {
                withContext(Dispatchers.IO) {
                    val newOffers = offersRepository.getOffers()
                    return@withContext newOffers
                }
            }

            val newVacancies = async {
                withContext(Dispatchers.IO) {
                    val newVacancies = vacanciesRepository.getVacancies()
                    return@withContext newVacancies
                }
            }
            val offersToShow = newOffers.await()
            val vacanciesToShow = newVacancies.await()
            vacanciesToShow.forEach {
                if (it.isFavorite) {
                    vacancyDataBase.getVacancyDao().insertVacancy(it)
                }
            }
            _offers.value = offersToShow
            _vacancies.value = vacanciesToShow
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyApp
                val offersRepository = application.appComponent.offersRepository()
                val vacanciesRepository = application.appComponent.vacanciesRepository()
                val vacancyDataBase = application.appComponent.vacancyDataBase()
                VacanciesViewModel(
                    offersRepository = offersRepository,
                    vacanciesRepository = vacanciesRepository,
                    vacancyDataBase = vacancyDataBase
                )
            }
        }
    }
}