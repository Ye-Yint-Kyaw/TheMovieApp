package com.yeyintkyaw.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate
import com.yeyintkyaw.themovieapp.viewholders.MovieViewHolder

class MovieAdapter(private val mDelegate: MovieViewHolderDelegate): RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList: List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view, mDelegate)
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    @SuppressLint("NotifySetDataChanged")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}