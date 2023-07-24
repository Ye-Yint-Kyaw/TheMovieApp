package com.yeyintkyaw.themovieapp.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.yeyintkyaw.themovieapp.data.vos.ACTORS
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.data.vos.NOW_PLAYING
import com.yeyintkyaw.themovieapp.data.vos.POPULAR
import com.yeyintkyaw.themovieapp.data.vos.TOP_RATED
import com.yeyintkyaw.themovieapp.network.responses.ActorListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {

//    private var mMovieList: MutableList<MovieVO> = mutableListOf()

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //Database
//       onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        // Network
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.result?.forEach { movie ->
                    movie.type = NOW_PLAYING
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.result ?: listOf())
//                onSuccess(it.result ?: listOf() )
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)
    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())
        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.result?.forEach { movie ->
                    movie.type = POPULAR
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.result ?: listOf())
//                onSuccess(it.result ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED) ?: listOf())
        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.result?.forEach { movie ->
                    movie.type = TOP_RATED
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.result ?: listOf())
//                onSuccess(it.result ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED)
    }



    @SuppressLint("CheckResult")
    override fun getPopularActors(

    ): LiveData<List<ActorVO>>?{
        mMovieApi.getPopularActors(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({actorResponse->
                actorResponse.results?.let {
                    mMovieDatabase?.actorDao()?.insertActors(it)
                }

            }, {

            })
        return mMovieDatabase?.actorDao()?.getAllActors()
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO>? {

        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })


        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    @SuppressLint("CheckResult")
    override fun getSearchMovies(query: String): Observable<List<MovieVO>> {

//        return mMovieApi
//            .getSearchMovies(query = query)

       // query = "h"

            return mMovieApi
                .getSearchMovies(query = query)
                .map { it.result ?: listOf() }
                .onErrorResumeNext { Observable.just(listOf()) }
                .subscribeOn(Schedulers.io())

//        mMovieList.clear()
//
//        mMovieApi
//            .getSearchMovies(query = query)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { movieListResponse ->
//
//                movieListResponse.result?.let {
//                    mMovieList.addAll(it)
//                }
//
//            }
//
//        return mMovieList

    }


}