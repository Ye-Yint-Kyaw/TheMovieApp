package com.yeyintkyaw.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.GenreVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO

class MovieTypeConverter {
    @TypeConverter
    fun toString(genreList: List<MovieVO>?): String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListJSONString: String): List<MovieVO>?{
        val genreListType = object : TypeToken<List<MovieVO>?>(){}.type
        return Gson().fromJson(genreListJSONString, genreListType)
    }
}