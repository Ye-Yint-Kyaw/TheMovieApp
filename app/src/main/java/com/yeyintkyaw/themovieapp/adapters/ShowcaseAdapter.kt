package com.yeyintkyaw.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.ShowcaseVIewHolderDelegate
import com.yeyintkyaw.themovieapp.viewholders.ShowcaseViewHolder

class ShowcaseAdapter(private val mDelegate: ShowcaseVIewHolderDelegate): RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_show_case, parent, false)
        return ShowcaseViewHolder(view, mDelegate)

    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    @SuppressLint("NotifyDataSetChange")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }

}