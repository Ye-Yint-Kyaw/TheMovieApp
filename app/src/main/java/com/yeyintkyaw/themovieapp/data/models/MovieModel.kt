package com.yeyintkyaw.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.network.responses.ActorListResponse
import io.reactivex.rxjava3.core.Observable

interface MovieModel {
    fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularActors(
    ): LiveData<List<ActorVO>>?

    fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO>?

    fun getSearchMovies(
        query: String
    ): Observable<List<MovieVO>>
}