package com.example.nasa_apod

import android.app.Application
import com.example.nasa_apod.di.DaggerNasaDataComponent
import com.example.nasa_apod.di.DatabaseModule
import com.example.nasa_apod.di.NasaDataComponent


class NasaApplicationClass : Application() {
    lateinit var nasaDataComponent: NasaDataComponent

    override fun onCreate() {
        super.onCreate()
        nasaDataComponent =
            DaggerNasaDataComponent.builder().databaseModule(DatabaseModule(this)).build()

    }
}