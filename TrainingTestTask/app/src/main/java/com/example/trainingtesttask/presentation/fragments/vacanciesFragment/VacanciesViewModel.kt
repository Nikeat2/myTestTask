package com.example.trainingtesttask.presentation.fragments.vacanciesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trainingtesttask.data.models.Offer
import com.example.trainingtesttask.di.MyApp
import com.example.trainingtesttask.domain.offersRepository.OffersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VacanciesViewModel @Inject constructor(offersRepository: OffersRepository) : ViewModel() {

    private var _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()

    init {
        viewModelScope.launch {
            val newOffers = async {
                withContext(Dispatchers.IO) {
                    val newOffers = offersRepository.getOffers()
                    return@withContext newOffers
                }
            }
            val offersToShow = newOffers.await()
            _offers.value = offersToShow

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyApp
                val repository = application.appComponent.offersRepository()
                VacanciesViewModel(repository)
            }
        }
    }
}