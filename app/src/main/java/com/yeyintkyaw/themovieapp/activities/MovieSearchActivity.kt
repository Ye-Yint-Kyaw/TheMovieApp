package com.yeyintkyaw.themovieapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding4.widget.textChanges
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.adapters.MovieAdapter
import com.yeyintkyaw.themovieapp.data.models.MovieModel
import com.yeyintkyaw.themovieapp.data.models.MovieModelImpl
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MovieSearchActivity:AppCompatActivity(), MovieViewHolderDelegate {

    private lateinit var mMovieAdapter: MovieAdapter
    private var mMovieModel = MovieModelImpl

    companion object{
        private const val SEARCH_MOVIE = "SEARCH_MOVIE"
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MovieSearchActivity::class.java)
            intent.putExtra(SEARCH_MOVIE, 0)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpListeners()
        setUpRecyclerView()
    }


    @SuppressLint("CheckResult")
    private fun setUpListeners(){
        val etSearch: TextInputEditText = findViewById(R.id.etSearch)
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap {
                mMovieModel.getSearchMovies(it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                Log.d("error", it.toString())
            })

    }

    private fun setUpRecyclerView(){
        val rvMovies: RecyclerView = findViewById(R.id.rvMovies)
        mMovieAdapter = MovieAdapter(this)
        rvMovies.adapter = mMovieAdapter
        rvMovies.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onTapMovie(movieVO: MovieVO) {
        startActivity(MovieDetailsActivity.newIntent(this, movieVO.id))
    }
}