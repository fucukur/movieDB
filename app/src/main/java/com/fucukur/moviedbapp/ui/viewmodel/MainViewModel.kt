package com.fucukur.moviedbapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fucukur.moviedbapp.domain.usecase.NowPlaying
import com.fucukur.moviedbapp.domain.usecase.UpComing
import com.fucukur.moviedbapp.ui.state.MainState
import com.fucukur.moviedbapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val upComingCase: UpComing,
    private val nowPlayingCase: NowPlaying,
) : ViewModel() {
    private val _nowPlaying = MutableStateFlow(MainState())
    val nowPlaying: StateFlow<MainState>
        get() = _nowPlaying

    private val _upComing = MutableStateFlow(MainState())
    val upComing: StateFlow<MainState>
        get() = _upComing

    init {
        getUpComingMovies()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        nowPlayingCase().onEach {
            when (it) {
                is Result.Loading -> {
                    _nowPlaying.value = MainState(isLoading = false)
                }
                is Result.Success -> {
                    _nowPlaying.value = MainState(data = it.data)
                }
                is Result.Error -> {
                    _nowPlaying.value = MainState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpComingMovies() {
        upComingCase().onEach {
            when (it) {
                is Result.Loading -> {
                    _upComing.value = MainState(isLoading = false)
                }
                is Result.Success -> {
                    _upComing.value = MainState(data = it.data)
                }
                is Result.Error -> {
                    _upComing.value = MainState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}