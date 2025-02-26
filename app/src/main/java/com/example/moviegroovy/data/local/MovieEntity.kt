package com.example.moviegroovy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviegroovy.data.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Double,
    val description: String
)

fun MovieEntity.toDomainMovie(): Movie {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    return Movie(
        id = this.id,
        title = this.title,
        rating = this.rating,
        imageUrl = baseUrl + this.posterPath,
        description = this.description
    )
}
