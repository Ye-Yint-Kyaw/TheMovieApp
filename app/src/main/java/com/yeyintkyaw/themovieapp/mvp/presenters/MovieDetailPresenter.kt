package com.yeyintkyaw.themovieapp.mvp.presenters

import com.yeyintkyaw.themovieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter {
    fun initView(view: MovieDetailView)
}