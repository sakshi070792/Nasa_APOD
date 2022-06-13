package com.example.nasa_apod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nasa_apod.model.NasaData

@Database(entities = [NasaData::class], version = 1)
abstract class FavouritesDatabase:RoomDatabase() {
    abstract fun getFavouritesDao():FavouritesDao

}