package com.example.nasa_apod.di

import com.example.nasa_apod.FavouriteListActivity
import com.example.nasa_apod.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class,DatabaseModule::class])
@Singleton
interface NasaDataComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(favouriteListActivity: FavouriteListActivity)
}

