package com.yeyintkyaw.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yeyintkyaw.themovieapp.persistence.typeconverters.MovieTypeConverter

@Entity(tableName = "actors")
data class ActorVO(
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("known_for")
    @ColumnInfo("known_for")
    val knownFor: List<MovieVO>?,

    @SerializedName("popularity")
    @ColumnInfo("popularity")
    val popularity: Double?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name: String?,

    @SerializedName("profile_path")
    @ColumnInfo("profile_path")
    val profilePath: String?,

    @SerializedName("gender")
    @ColumnInfo("gender")
    val gender: Int?,

    @SerializedName("known_for_department")
    @ColumnInfo("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("original_name")
    @ColumnInfo("original_name")
    val originalName: String?,

    @SerializedName("cast_id")
    @ColumnInfo("cast_id")
    val castId: Int?,

    @SerializedName("character")
    @ColumnInfo("character")
    val character: String?,

    @SerializedName("credit_id")
    @ColumnInfo("credit_id")
    val creditId: String?,

    @SerializedName("order")
    @ColumnInfo("order")
    val order: Int?,
)

const val ACTORS = "ACTOR"
const val CREATORS = "CREATOR"

