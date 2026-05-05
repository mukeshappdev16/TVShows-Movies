package com.ms.moviestvshows.presentation.trending

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.ms.moviestvshows.presentation.common.components.HeroSection
import com.ms.moviestvshows.presentation.common.components.StandardCard

@Composable
fun TrendingScreen(
    state: TrendingState,
    windowSizeClass: WindowSizeClass,
    onMovieClick: (Int) -> Unit,
    onTvSeriesClick: (Int) -> Unit
) {
    val isExpanded = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
    val cardWidth = if (isExpanded) 180.dp else 130.dp
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (state.trendingAll.isNotEmpty()) {
                val heroItem = state.trendingAll.first()
                HeroSection(
                    title = heroItem.title,
                    overview = heroItem.overview,
                    posterPath = heroItem.posterPath,
                    modifier = Modifier.padding(bottom = 24.dp),
                    windowSizeClass = windowSizeClass,
                    onClick = {
                        if (heroItem.mediaType == "movie") {
                            onMovieClick(heroItem.id)
                        } else if (heroItem.mediaType == "tv") {
                            onTvSeriesClick(heroItem.id)
                        }
                    }
                )
            }

            TrendingSection("Trending Movies", state.trendingMovies) { items ->
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items) { item ->
                        StandardCard(
                            title = item.title,
                            posterPath = item.posterPath,
                            voteAverage = item.voteAverage,
                            modifier = Modifier.width(cardWidth),
                            onClick = { onMovieClick(item.id) }
                        )
                    }
                }
            }

            TrendingSection("Trending TV Shows", state.trendingTvSeries) { items ->
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items) { item ->
                        StandardCard(
                            title = item.name,
                            posterPath = item.posterPath,
                            voteAverage = item.voteAverage,
                            modifier = Modifier.width(cardWidth),
                            onClick = { onTvSeriesClick(item.id) }
                        )
                    }
                }
            }

            TrendingSection("Trending People", state.trendingPeople) { items ->
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items) { item ->
                        StandardCard(
                            title = item.name,
                            posterPath = item.profilePath,
                            modifier = Modifier.width(cardWidth)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
fun <T> TrendingSection(
    title: String,
    items: List<T>,
    onSeeAllClick: () -> Unit = {},
    content: @Composable (List<T>) -> Unit,
) {
    if (items.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 12.dp),
            )
            TextButton(onClick = onSeeAllClick) {
                Text(text = "See all", color = MaterialTheme.colorScheme.primary)
            }
        }
        content(items)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
