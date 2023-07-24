package com.yeyintkyaw.themovieapp.delegates

import com.yeyintkyaw.themovieapp.data.vos.MovieVO

interface MovieViewHolderDelegate {
    fun onTapMovie(movieVO: MovieVO)
}