package com.example.exampleretrofit.model.network

import com.example.exampleretrofit.model.data.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = "ba042ff0f78d77cbf99f341cdc2b9e51"
    ) : Response<MovieListModel>
}