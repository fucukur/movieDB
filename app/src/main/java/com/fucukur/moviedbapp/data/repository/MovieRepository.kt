package com.fucukur.moviedbapp.data.repository

import android.util.Log
import com.fucukur.moviedbapp.data.service.MovieService
import com.fucukur.moviedbapp.data.model.MovieResponse
import com.fucukur.moviedbapp.domain.repository.MovieRepoInterface
import com.fucukur.moviedbapp.util.Constants.api_key
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: MovieService) : MovieRepoInterface {

    override suspend fun nowPlayingMovies(page: Int): MovieResponse {
        Log.e("XXX Retrofit Log -> ", service.toString())
        return service.getNowPlaying(api_key, page)
    }

    override suspend fun upcomingMovies(page: Int): MovieResponse {
        Log.e("XXX Retrofit Log -> ", page.toString())
        return service.getUpcoming(api_key, page)
    }
}