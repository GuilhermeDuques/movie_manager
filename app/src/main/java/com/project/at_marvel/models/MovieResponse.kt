package com.project.at_marvel.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies : kotlin.collections.List<Movie>

) : Parcelable {
    constructor() : this(mutableListOf())
}