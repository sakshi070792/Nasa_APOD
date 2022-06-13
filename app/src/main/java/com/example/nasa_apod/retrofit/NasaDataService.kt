package com.example.nasa_apod.retrofit

import com.example.nasa_apod.model.NasaData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaDataService {
@GET("apod")
    suspend fun getNasaData(@Query("api_key") api_key:String,@Query("date")date:String):Response<NasaData>

}