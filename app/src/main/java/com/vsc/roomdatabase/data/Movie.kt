package com.vsc.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val movieId: Long? = null,
    @ColumnInfo(name = "movieName") val movieName: String?,
    @ColumnInfo(name = "yearOfRelease") val movieYear: Int?,
    @ColumnInfo(name = "rating") val movieRating: Int?
)