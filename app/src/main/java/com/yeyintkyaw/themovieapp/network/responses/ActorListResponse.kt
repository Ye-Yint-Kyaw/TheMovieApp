package com.yeyintkyaw.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.yeyintkyaw.themovieapp.data.vos.ActorVO

data class ActorListResponse(

    @SerializedName("results")
    val results: List<ActorVO>?,
)
