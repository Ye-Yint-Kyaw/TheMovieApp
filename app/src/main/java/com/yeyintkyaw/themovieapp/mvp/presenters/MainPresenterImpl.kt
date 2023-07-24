package com.yeyintkyaw.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.yeyintkyaw.themovieapp.activities.MainActivity
import com.yeyintkyaw.themovieapp.data.models.MovieModel
import com.yeyintkyaw.themovieapp.data.vos.GenreVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(), MainPresenter {

    //View
    private var mView: MainView? = null

    //Model
    private val mMovieModel: MovieModel? = null

    //State
    private val mGenre: List<GenreVO>? = null


    fun initView(view: MainActivity) {
        mView = view
    }

    override fun initView(view: MainView) {

    }

    override fun onTapGenre(genrePosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onUiReady(owner: LifecycleOwner) {

        //Now Playing
        mMovieModel?.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        //Popular
        mMovieModel?.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }

        //Top Rated
        mMovieModel?.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        //Get Actors
        mMovieModel?.getPopularActors()?.observe(owner){
            mView?.showActors(it)
        }


    }


    override fun onTapMovieFromBanner(movieVO: MovieVO) {
        mView?.showNavigateToMovieDetailScreen(movieVO.id)
    }

    override fun onTapMovieFromShowcase(movieVO: MovieVO) {
        mView?.showNavigateToMovieDetailScreen(movieVO.id)
    }

    override fun onTapMovie(movieVO: MovieVO) {
        mView?.showNavigateToMovieDetailScreen(movieVO.id)
    }
}