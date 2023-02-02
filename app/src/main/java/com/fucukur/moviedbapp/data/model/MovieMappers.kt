package com.fucukur.moviedbapp.data.model

import com.fucukur.moviedbapp.domain.model.NetworkMovie

fun Movie.mapMovie() = NetworkMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath, ///TODO: Data geliyor path oluşturulacak.
    realease = realease,
    voteAverage = voteAverage,
    backdrop = "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg" ///TODO: Endpoint ten kontrol edilecek.
    //backdrop = backdrop_path
)