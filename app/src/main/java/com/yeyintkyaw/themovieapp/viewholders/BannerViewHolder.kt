package com.yeyintkyaw.themovieapp.viewholders

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.BannerViewHolderDelegate
import com.yeyintkyaw.themovieapp.utils.IMAGE_BASE_URL

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
   var movieVO: MovieVO? = null

    init {
        itemView.setOnClickListener{
            movieVO?.let { mv ->
                mDelegate.onTapMovieFromBanner(mv)
            }
        }
    }


    fun bindData(movie: MovieVO){
        this.movieVO = movie
        val ivBannerImage: ImageView = itemView.findViewById(R.id.ivBannerImage)
        val tvBannerMovieName: TextView = itemView.findViewById(R.id.tvBannerMovieName)
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(ivBannerImage)
        tvBannerMovieName.text = movie.title
    }
}