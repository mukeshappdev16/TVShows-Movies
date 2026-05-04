package com.ms.moviestvshows.presentation.celebrites

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
import com.ms.moviestvshows.domain.model.Person

@Composable
fun CelebritiesScreen(state: CelebritiesState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
        ) {
            PeopleSection("Popular People", state.popularPeople)
            PeopleSection("Trending People", state.trendingPeople)
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
fun PeopleSection(
    title: String,
    people: List<Person>,
    onSeeAllClick: () -> Unit = {},
) {
    if (people.isNotEmpty()) {
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
        LazyRow {
            items(people) { person ->
                PersonItem(person)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PersonItem(person: Person) {
    Column(
        modifier =
            Modifier
                .width(120.dp)
                .padding(end = 8.dp),
    ) {
        AsyncImage(
            model = "${TmdbApi.IMAGE_BASE_URL}${person.profilePath}",
            contentDescription = person.name,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(180.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = person.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
