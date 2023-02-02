package com.fucukur.moviedbapp.data.di

import android.util.Log
import com.fucukur.moviedbapp.data.repository.MovieRepository
import com.fucukur.moviedbapp.data.service.MovieService
import com.fucukur.moviedbapp.domain.repository.MovieRepoInterface
import com.fucukur.moviedbapp.util.Constants.base_url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.e("Base Url Control", base_url)
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        Log.e("Retofit Base URL ", retrofit.baseUrl().toString())
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(service: MovieService): MovieRepoInterface {
        return MovieRepository(service)
    }
}