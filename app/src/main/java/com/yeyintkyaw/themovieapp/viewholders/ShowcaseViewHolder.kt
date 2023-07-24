package com.yeyintkyaw.themovieapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.ShowcaseVIewHolderDelegate
import com.yeyintkyaw.themovieapp.utils.BASE_URL
import com.yeyintkyaw.themovieapp.utils.IMAGE_BASE_URL

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowcaseVIewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { mv->
                mDelegate.onTapMovieFromShowcase(mv)
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        val ivShowCase: ImageView = itemView.findViewById(R.id.ivShowcaseImage)
        val tvShowcaseName: TextView = itemView.findViewById(R.id.tvShowcaseName)
        val tvShowcaseMovieDate: TextView = itemView.findViewById(R.id.tvShowcaseDate)

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(ivShowCase)
        tvShowcaseName.text = movie.title
        tvShowcaseMovieDate.text = movie.releaseDate
    }


}