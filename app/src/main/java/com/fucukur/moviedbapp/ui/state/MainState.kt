package com.fucukur.moviedbapp.ui.state


import com.fucukur.moviedbapp.domain.model.NetworkMovie

data class MainState(
    val error : String = "",
    val isLoading : Boolean = false,
    val data: List<NetworkMovie>? = null
)
