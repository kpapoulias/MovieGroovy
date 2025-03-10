package com.example.moviegroovy.data.model

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Double,
    val voteCount: Int,
    val description: String
)