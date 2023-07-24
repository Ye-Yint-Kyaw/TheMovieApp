package com.yeyintkyaw.themovieapp.mvp.views

import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.GenreVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO

interface MainView: BaseView {
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showActors(actorList: List<ActorVO>)
    fun showNavigateToMovieDetailScreen(movieId: Int)
}