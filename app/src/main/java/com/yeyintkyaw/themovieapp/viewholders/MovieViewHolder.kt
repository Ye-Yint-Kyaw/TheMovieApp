package com.yeyintkyaw.themovieapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate
import com.yeyintkyaw.themovieapp.utils.IMAGE_BASE_URL

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { mv->
                mDelegate.onTapMovie(mv)
            }
        }

    }
    fun bindData(movie: MovieVO){

        mMovieVO = movie
        val ivMovieImage: ImageView = itemView.findViewById(R.id.ivMovieImage)
        val tvMovieName: TextView = itemView.findViewById(R.id.tvMovieName)
        val tvMovieRating: TextView = itemView.findViewById(R.id.tvMovieRating)
        val rbMovieRating: RatingBar = itemView.findViewById(R.id.rbMovieRating)
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieImage)
        tvMovieName.text = movie.title
        tvMovieRating.text = movie.voteAverage?.toString()
        rbMovieRating.rating = movie.getRatingBasedOnFiveStars()

    }
}