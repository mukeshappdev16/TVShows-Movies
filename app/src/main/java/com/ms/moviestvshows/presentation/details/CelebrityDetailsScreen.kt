package com.ms.moviestvshows.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.KnownFor
import com.ms.moviestvshows.presentation.common.components.StandardCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelebrityDetailsScreen(
    state: CelebrityDetailsState,
    onBackClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onTvSeriesClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.celebrityDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = "${TmdbApi.IMAGE_BASE_URL}${details.profilePath}",
                        contentDescription = details.name,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = details.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = details.knownForDepartment,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                        details.birthday?.let { birthday ->
                            Text(
                                text = "Born: $birthday",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        details.placeOfBirth?.let { place ->
                            Text(
                                text = place,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }

                if (details.biography.isNotEmpty()) {
                    Text(
                        text = "Biography",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    )
                    Text(
                        text = details.biography,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    )
                }

                if (details.knownFor.isNotEmpty()) {
                    KnownForSection(
                        items = details.knownFor,
                        onMovieClick = onMovieClick,
                        onTvSeriesClick = onTvSeriesClick
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
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
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
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
fun KnownForSection(
    items: List<KnownFor>,
    onMovieClick: (Int) -> Unit,
    onTvSeriesClick: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Known For",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                StandardCard(
                    title = item.title,
                    posterPath = item.posterPath,
                    voteAverage = item.voteAverage,
                    modifier = Modifier.width(130.dp),
                    onClick = {
                        if (item.mediaType == "movie") {
                            onMovieClick(item.id)
                        } else if (item.mediaType == "tv") {
                            onTvSeriesClick(item.id)
                        }
                    }
                )
            }
        }
    }
}
