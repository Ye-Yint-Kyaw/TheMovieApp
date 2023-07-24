package com.yeyintkyaw.themovieapp.delegates

import com.yeyintkyaw.themovieapp.data.vos.MovieVO

interface ShowcaseVIewHolderDelegate {
    fun onTapMovieFromShowcase(movieVO: MovieVO)
}