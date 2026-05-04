package com.ms.moviestvshows.presentation.trending

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.TrendingItem
import com.ms.moviestvshows.presentation.celebrites.PersonItem
import com.ms.moviestvshows.presentation.movies.MovieItem
import com.ms.moviestvshows.presentation.shows.TvSeriesItem

@Composable
fun TrendingScreen(state: TrendingState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
        ) {
            TrendingSection("Trending All", state.trendingAll) { items ->
                LazyRow {
                    items(items) { item ->
                        TrendingAllItem(item)
                    }
                }
            }
            TrendingSection("Trending Movies", state.trendingMovies) { items ->
                LazyRow {
                    items(items) { item ->
                        MovieItem(item)
                    }
                }
            }
            TrendingSection("Trending TV Shows", state.trendingTvSeries) { items ->
                LazyRow {
                    items(items) { item ->
                        TvSeriesItem(item)
                    }
                }
            }
            TrendingSection("Trending People", state.trendingPeople) { items ->
                LazyRow {
                    items(items) { item ->
                        PersonItem(item)
                    }
                }
            }
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            TextButton(onClick = onSeeAllClick) {
                Text(text = "See all")
            }
        }
        content(items)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TrendingAllItem(item: TrendingItem) {
    Column(
        modifier =
            Modifier
                .width(120.dp)
                .padding(end = 8.dp),
    ) {
        AsyncImage(
            model = "${TmdbApi.IMAGE_BASE_URL}${item.posterPath}",
            contentDescription = item.title,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(180.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
