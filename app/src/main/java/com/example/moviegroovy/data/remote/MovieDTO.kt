package com.example.moviegroovy.data.remote

import com.google.gson.annotations.SerializedName
import com.example.moviegroovy.data.local.MovieEntity
import com.example.moviegroovy.data.model.Movie

data class MovieResponse(
    @SerializedName("results") val results: List<MovieDTO>
)

data class MovieDTO(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val rating: Double,
    val overview: String
) {
    fun toMovieEntity() = MovieEntity(id, title, posterPath, rating, overview)

    fun toDomainMovie() = Movie(
        id = id,
        title = title,
        imageUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        rating = rating,
        description = overview
    )
}
