package com.fucukur.moviedbapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val results: List<NetworkMovie>,
    val totalPages: Int,
    val totalResult: Int,
) : Parcelable
