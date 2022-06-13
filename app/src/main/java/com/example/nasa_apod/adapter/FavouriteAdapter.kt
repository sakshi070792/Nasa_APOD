package com.example.nasa_apod.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_apod.R
import com.example.nasa_apod.model.NasaData
import com.squareup.picasso.Picasso

class FavouriteAdapter(private var favList: List<NasaData>) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private lateinit var context: Context

    inner class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.textItem)
        var imageNasa: ImageView = itemView.findViewById(R.id.imageNasaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_main, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.title.text = favList[position].title
        Picasso.with(context).load(favList[position].url).into(holder.imageNasa)
    }

    override fun getItemCount(): Int {
        return favList.size
    }
}