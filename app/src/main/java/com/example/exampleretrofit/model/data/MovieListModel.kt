package com.example.exampleretrofit.model.data

data class MovieListModel(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)
