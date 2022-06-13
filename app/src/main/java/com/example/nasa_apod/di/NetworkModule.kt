package com.example.nasa_apod.di

import com.example.nasa_apod.retrofit.NasaDataService
import com.example.nasa_apod.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
      return Retrofit.Builder().baseUrl(Constants.BASE_URL) .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getNasaDataService(retrofit: Retrofit):NasaDataService{
        return retrofit.create(NasaDataService::class.java)
    }
}