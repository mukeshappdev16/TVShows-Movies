package com.ms.moviestvshows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ms.moviestvshows.presentation.common.MainScreen
import com.ms.moviestvshows.ui.theme.MoviesTVShowsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTVShowsTheme {
                MainScreen()
            }
        }
    }
}
