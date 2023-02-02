package com.fucukur.moviedbapp.domain.repository

import com.fucukur.moviedbapp.data.model.MovieResponse

interface MovieRepoInterface {
    suspend fun nowPlayingMovies(page: Int): MovieResponse
    suspend fun upcomingMovies(page: Int): MovieResponse
}