package com.example.moviegroovy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviegroovy.R
import com.example.moviegroovy.data.model.Movie


@Composable
fun MovieDetailScreen(
    movie: Movie,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),

            ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = movie.imageUrl,
                    error = painterResource(R.drawable.image_24px)
                ),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(600.dp)
                    .clip(RoundedCornerShape(20.dp)),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "⭐ ${movie.rating}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "(${movie.voteCount})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.description ?: "No description available",
            fontSize = 16.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = onBackClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieDetailScreen() {
    val sampleMovie = Movie(
        id = 1,
        title = "Inception",
        imageUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        rating = 8.8,
        voteCount = 100,
        description = "A thief who enters the dreams of others to steal secrets from their subconscious."
    )

    MovieDetailScreen(
        movie = sampleMovie,
        onBackClick = {}
    )
}