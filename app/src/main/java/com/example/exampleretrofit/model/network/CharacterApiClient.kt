package com.example.exampleretrofit.model.network

import com.example.exampleretrofit.model.data.CharacterListModel
import com.example.exampleretrofit.model.data.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {

    @GET("character/")
    suspend fun getCharactersList(@Query("page") page: Int): Response<CharacterListModel>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterModel>
}