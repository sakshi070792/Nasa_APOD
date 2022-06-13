package com.example.nasa_apod.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasa_apod.db.FavouritesDatabase
import com.example.nasa_apod.model.NasaData
import com.example.nasa_apod.retrofit.NasaDataService
import com.example.nasa_apod.utils.NetworkUtils
import javax.inject.Inject

class NasaDataRepository @Inject constructor(
    private val nasaDataService: NasaDataService,
    private val application: Application,
    private val favouritesDatabase: FavouritesDatabase
) {

    private val nasaData = MutableLiveData<NasaData>()

    val nasaLiveData: LiveData<NasaData>
        get() = nasaData


    suspend fun getNasaData(apiKey: String, date: String) {
        if (NetworkUtils.isNetworkAvailable(application.applicationContext)) {
            val result = nasaDataService.getNasaData(apiKey, date)
            if (result.body() != null) {
                nasaData.postValue(result.body())
            }
        } else {
            val post = favouritesDatabase.getFavouritesDao().getFavourites()
            for (index in post.indices) {
                if (index == post.size - 1) {
                    nasaData.postValue(post[index])
                }
            }

        }
    }
}