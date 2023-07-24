package com.yeyintkyaw.themovieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.persistence.daos.MovieDao
import com.yeyintkyaw.themovieapp.persistence.MovieDatabase
import com.yeyintkyaw.themovieapp.persistence.daos.ActorDao
import com.yeyintkyaw.themovieapp.persistence.typeconverters.MovieTypeConverter

@Database(
    entities = [
        MovieVO::class,
        ActorVO::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MovieTypeConverter::class
)
abstract class MovieDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "THE_MOVIE_DB"

        var dbInstant: MovieDatabase? = null

        fun getDBInstant(context: Context): MovieDatabase? {
            when (dbInstant) {
                null -> {
                    dbInstant = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstant
        }
    }

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
}