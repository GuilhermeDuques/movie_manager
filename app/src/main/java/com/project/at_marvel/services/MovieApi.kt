package com.project.at_marvel.services

import com.project.at_marvel.models.MovieResponse
import retrofit2.http.GET
import retrofit2.Call


interface MovieApi {
    @GET("/3/movie/popular?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
fun getMovieList(): Call<MovieResponse>
}