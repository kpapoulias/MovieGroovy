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
    val voteCount: Int,
    val description: String
)

fun MovieEntity.toDomainMovie(): Movie {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    return Movie(
        id = this.id,
        title = this.title,
        imageUrl = baseUrl + this.posterPath,
        rating = this.rating,
        voteCount = this.voteCount,
        description = this.description
    )
}
