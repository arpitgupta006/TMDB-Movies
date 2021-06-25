package com.arpit.newsapp3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.tmdbmovies.R
import com.arpit.tmdbtopmovies.ResponseMovie
import com.arpit.tmdbtopmovies.ResultsItem
import com.bumptech.glide.Glide

class MyAdapter( val context: Context , val movies: List<ResultsItem> ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvreleaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var ivPosterImage = itemView.findViewById<ImageView>(R.id.ivPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.tvTitle.text = movies[position].originalTitle
        holder.tvDescription.text = movies[position].overview
        holder.tvreleaseDate.text = movies[position].releaseDate
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movies[position].posterPath).into(holder.ivPosterImage)

    }

    override fun getItemCount() = movies.size
}

