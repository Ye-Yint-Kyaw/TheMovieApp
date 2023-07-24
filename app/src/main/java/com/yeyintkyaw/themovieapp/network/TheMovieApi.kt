package com.yeyintkyaw.themovieapp.network

import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.network.responses.ActorListResponse
import com.yeyintkyaw.themovieapp.network.responses.MovieListResponse
import com.yeyintkyaw.themovieapp.utils.API_GET_ACTORS
import com.yeyintkyaw.themovieapp.utils.API_GET_NOW_PLAYING
import com.yeyintkyaw.themovieapp.utils.API_GET_POPULAR_MOVIES
import com.yeyintkyaw.themovieapp.utils.API_GET_TOP_RATED_MOVIES
import com.yeyintkyaw.themovieapp.utils.API_GET_MOVIE_DETAILS
import com.yeyintkyaw.themovieapp.utils.API_SEARCH_MOVIES
import com.yeyintkyaw.themovieapp.utils.MOVIE_API_KEY
import com.yeyintkyaw.themovieapp.utils.PARAM_API_KEY
import com.yeyintkyaw.themovieapp.utils.PARAM_PAGE
import com.yeyintkyaw.themovieapp.utils.PARAM_QUERY
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
       @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
       @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getPopularActors(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<ActorListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Observable<MovieVO>

    @GET(API_SEARCH_MOVIES)
    fun getSearchMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query: String
    ): Observable<MovieListResponse>
}