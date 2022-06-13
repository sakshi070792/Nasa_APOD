package com.example.nasa_apod.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasa_apod.repository.NasaDataRepository
import javax.inject.Inject

class NasaDataViewModelFactory @Inject constructor(private val nasaDataRepository: NasaDataRepository) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NasaDataViewModel(nasaDataRepository) as T
    }
}