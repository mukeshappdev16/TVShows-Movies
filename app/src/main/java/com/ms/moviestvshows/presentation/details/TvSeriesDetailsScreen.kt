package com.ms.moviestvshows.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.Season
import com.ms.moviestvshows.domain.model.TvSeries
import com.ms.moviestvshows.presentation.common.components.CastSection
import com.ms.moviestvshows.presentation.common.components.HeroSection
import com.ms.moviestvshows.presentation.common.components.StandardCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvSeriesDetailsScreen(
    state: TvSeriesDetailsState,
    windowSizeClass: WindowSizeClass,
    onBackClick: () -> Unit,
    onTvSeriesClick: (Int) -> Unit,
    onCastClick: (Int) -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        state.tvSeriesDetails?.let { series ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HeroSection(
                    title = series.name,
                    overview = series.tagline.ifEmpty { series.status },
                    posterPath = series.backdropPath ?: series.posterPath,
                    windowSizeClass = windowSizeClass,
                    onWatchTrailerClick = series.trailerKey?.let { key ->
                        {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
                            context.startActivity(intent)
                        }
                    }
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = series.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (series.tagline.isNotEmpty()) {
                        Text(
                            text = series.tagline,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = String.format("%.1f", series.voteAverage),
                            modifier = Modifier.padding(start = 4.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = series.firstAirDate)
                    }
                    
                    Text(
                        text = "${series.numberOfSeasons} Seasons • ${series.numberOfEpisodes} Episodes",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Row(
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        series.genres.forEach { genre ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = genre,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }

                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                    Text(
                        text = series.overview,
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    if (series.cast.isNotEmpty()) {
                        CastSection(cast = series.cast, onCastClick = onCastClick)
                    }

                    if (series.seasons.isNotEmpty()) {
                        SeasonsSection(seasons = series.seasons)
                    }

                    if (series.similar.isNotEmpty()) {
                        SimilarTvSeriesSection(series = series.similar, onTvSeriesClick = onTvSeriesClick)
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }

        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* Handle Share */ }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.TopStart)
        )

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun SeasonsSection(seasons: List<Season>) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Seasons",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(seasons) { season ->
                StandardCard(
                    title = season.name,
                    posterPath = season.posterPath,
                    modifier = Modifier.width(130.dp),
                )
            }
        }
    }
}

@Composable
private fun SimilarTvSeriesSection(series: List<TvSeries>, onTvSeriesClick: (Int) -> Unit) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Similar TV Shows",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(series) { item ->
                StandardCard(
                    title = item.name,
                    posterPath = item.posterPath,
                    voteAverage = item.voteAverage,
                    modifier = Modifier.width(130.dp),
                    onClick = { onTvSeriesClick(item.id) }
                )
            }
        }
    }
}
