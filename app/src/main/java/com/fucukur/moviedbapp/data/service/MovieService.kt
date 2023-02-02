package com.fucukur.moviedbapp.data.service

import com.fucukur.moviedbapp.data.model.MovieResponse
import com.fucukur.moviedbapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") api_key: String = Constants.api_key,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") api_key: String = Constants.api_key,
        @Query("page") page: Int
    ): MovieResponse
}