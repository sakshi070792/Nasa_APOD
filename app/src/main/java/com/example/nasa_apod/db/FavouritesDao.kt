package com.example.nasa_apod.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nasa_apod.model.NasaData

@Dao
interface FavouritesDao {
    @Insert
   suspend fun saveFavourites(data: NasaData)

    @Query("Select * from favourites")
   suspend fun getFavourites(): List<NasaData>


}