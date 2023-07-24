package com.yeyintkyaw.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yeyintkyaw.themovieapp.persistence.typeconverters.CollectionTypeConverter
import com.yeyintkyaw.themovieapp.persistence.typeconverters.GenreIdsTypeConverter
import com.yeyintkyaw.themovieapp.persistence.typeconverters.GenreListTypeConverter

@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class
)

data class MovieVO(
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("belongs_to_collection")
   // @ColumnInfo(name = "belongs_to_collection")
    @Embedded("belongs_to_collection_")
    val belongsToCollection: CollectionVO?,

    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    val budget: Double?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,

    @SerializedName("homePage")
    @ColumnInfo(name = "homePage")
    val homePage: String?,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overView: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "type")
    var type: String?,



) {
    fun getRatingBasedOnFiveStars(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }
}
const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"