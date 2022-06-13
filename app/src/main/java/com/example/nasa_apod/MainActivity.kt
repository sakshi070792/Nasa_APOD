package com.example.nasa_apod

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.nasa_apod.databinding.ActivityMainBinding
import com.example.nasa_apod.db.FavouritesDatabase
import com.example.nasa_apod.viewmodel.NasaDataViewModel
import com.example.nasa_apod.viewmodel.NasaDataViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var nasaDataViewModelFactory: NasaDataViewModelFactory

    @Inject
    lateinit var favouritesDatabase: FavouritesDatabase

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.imageView.setOnClickListener {
            showDatePickerDialog()
        }
        (application as NasaApplicationClass).nasaDataComponent.inject(this)
        val nasaDataViewModel: NasaDataViewModel by viewModels { nasaDataViewModelFactory }
        binding.mainViewModel = nasaDataViewModel
        binding.lifecycleOwner = this
    }


    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                var month: String = "" + monthOfYear
                var day: String = "" + dayOfMonth
                if (monthOfYear < 10) {
                    month = "0$monthOfYear"
                }
                if (dayOfMonth < 10) {
                    day = "0$day"
                }
                // Display Selected date in textBox
                val text = "$year-$month-$day"
                binding.mainViewModel!!.getData(text)
                setDataToView()
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    private fun setDataToView() {
        binding.myToggleButton.visibility = View.VISIBLE
        binding.showButton.visibility = View.VISIBLE
        binding.myToggleButton.setBackgroundDrawable(
            ContextCompat.getDrawable(
                applicationContext, R.drawable.ic_baseline_favorite_border
            )
        )
        binding.myToggleButton.isChecked = false
        setObservable()

    }

    private fun setObservable() {
        binding.mainViewModel!!.liveData.observe(this) {
            binding.myToggleButton.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    binding.myToggleButton.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            applicationContext, R.drawable.ic_baseline_favorite
                        )
                    )
                    lifecycleScope.launch(Dispatchers.IO) {
                        favouritesDatabase.getFavouritesDao().saveFavourites(it)
                    }
                }
            }
            handleOnClickShowButton()
            Picasso.with(this).load(it.url).into(binding.imageNasa)
        }
    }

    private fun handleOnClickShowButton() {
        binding.showButton.setOnClickListener {
            val intent = Intent(this, FavouriteListActivity::class.java)
            startActivity(intent)
        }
    }
}