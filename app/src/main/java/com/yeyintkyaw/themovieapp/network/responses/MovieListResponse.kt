package com.yeyintkyaw.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.yeyintkyaw.themovieapp.data.vos.DateVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO

data class MovieListResponse (
    @SerializedName("page")
    val page: Int?,

    @SerializedName("dates")
    val dates : DateVO?,

    @SerializedName("results")
    val result : List<MovieVO>?
)