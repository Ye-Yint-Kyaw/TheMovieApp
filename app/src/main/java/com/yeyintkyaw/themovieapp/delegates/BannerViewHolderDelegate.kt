package com.yeyintkyaw.themovieapp.delegates

import com.yeyintkyaw.themovieapp.data.vos.MovieVO

interface BannerViewHolderDelegate  {
    fun onTapMovieFromBanner(movieVO: MovieVO)
}