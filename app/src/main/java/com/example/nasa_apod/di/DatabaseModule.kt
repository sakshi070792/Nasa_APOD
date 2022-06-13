package com.example.nasa_apod.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.nasa_apod.db.FavouritesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Singleton
    @Provides
    fun getRoomDb():FavouritesDatabase  {
        return  Room.databaseBuilder(application.applicationContext,FavouritesDatabase::class.java,"roomDb").build()
    }

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application
}