package com.example.nasa_apod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasa_apod.model.NasaData
import com.example.nasa_apod.repository.NasaDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NasaDataViewModel(private val nasaDataRepository: NasaDataRepository):ViewModel() {
    val liveData:LiveData<NasaData>
        get() =nasaDataRepository.nasaLiveData

  fun getData(date:String){
      viewModelScope.launch (Dispatchers.IO){
          nasaDataRepository.getNasaData("DEMO_KEY",date)
      }
  }
}