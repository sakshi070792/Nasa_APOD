package com.example.nasa_apod

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa_apod.adapter.FavouriteAdapter
import com.example.nasa_apod.databinding.FavouritesactvityMainBinding
import com.example.nasa_apod.db.FavouritesDatabase
import kotlinx.coroutines.*
import javax.inject.Inject

class FavouriteListActivity: AppCompatActivity() {

    @Inject
    lateinit var favouritesDatabase: FavouritesDatabase


    private lateinit var binding: FavouritesactvityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.favouritesactvity_main)
        (application as NasaApplicationClass).nasaDataComponent.inject(this)
        runBlocking {
            val valueDeferred=async { favouritesDatabase.getFavouritesDao().getFavourites() }
            if(valueDeferred.await().isNotEmpty()){
                val adapter=FavouriteAdapter(valueDeferred.await())
                val layoutManager = LinearLayoutManager(applicationContext)
                binding.recyclerList.layoutManager = layoutManager
                binding.recyclerList.adapter=adapter
            }
            else{
                Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()
            }
        }
    }
}
