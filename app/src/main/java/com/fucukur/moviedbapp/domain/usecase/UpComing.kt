package com.fucukur.moviedbapp.domain.usecase

import com.fucukur.moviedbapp.data.model.mapMovie
import com.fucukur.moviedbapp.domain.model.NetworkMovie
import com.fucukur.moviedbapp.domain.repository.MovieRepoInterface
import com.fucukur.moviedbapp.util.Result
import com.fucukur.moviedbapp.util.Value.page_counter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpComing @Inject constructor(private val repo: MovieRepoInterface) {
    operator fun invoke(): Flow<Result<List<NetworkMovie>>> = flow {
        try {
            emit(Result.Loading())
            val data = repo.upcomingMovies(page_counter)
            val domainData =
                data.results.map {
                    it.mapMovie()
                }
            emit(Result.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Result.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Result.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}