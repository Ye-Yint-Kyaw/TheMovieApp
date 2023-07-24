package com.yeyintkyaw.themovieapp.mvp.presenters

import com.yeyintkyaw.themovieapp.delegates.BannerViewHolderDelegate
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate
import com.yeyintkyaw.themovieapp.delegates.ShowcaseVIewHolderDelegate
import com.yeyintkyaw.themovieapp.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate, ShowcaseVIewHolderDelegate, MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}
