package com.yeyintkyaw.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.adapters.MovieAdapter
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
       // setUpMovieViewHolder()
    }

    fun setData(movieList: List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }

    fun setUpMovieListViewPod(delegate : MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieViewHolder()
    }


    private fun setDelegate(delegate : MovieViewHolderDelegate){
    this.mDelegate = delegate
    }

    private fun setUpMovieViewHolder(){
        val vrMovieList: RecyclerView = findViewById(R.id.vrMovieList)
        mMovieAdapter = MovieAdapter(mDelegate)
        vrMovieList.adapter = mMovieAdapter
        vrMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)

    }

}