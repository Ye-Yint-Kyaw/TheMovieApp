package com.yeyintkyaw.themovieapp

import android.app.Application
import com.yeyintkyaw.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}