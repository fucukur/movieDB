package com.fucukur.moviedbapp.domain.model

import android.os.Parcelable
import com.fucukur.moviedbapp.util.Constants
import com.fucukur.moviedbapp.util.Image.back_image
import com.fucukur.moviedbapp.util.Image.bigImage
import com.fucukur.moviedbapp.util.Image.postImage
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val realease: String,
    val voteAverage: Double?,
    val backdrop: String
) : Parcelable {
    @IgnoredOnParcel
    val posterUrl = postImage + posterPath

    @IgnoredOnParcel
    val backDrops = back_image + posterUrl

    ///TODO: Dummy Data servisler değişti kontrol edilecek.
    @IgnoredOnParcel
    val slider = "$bigImage/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"
}