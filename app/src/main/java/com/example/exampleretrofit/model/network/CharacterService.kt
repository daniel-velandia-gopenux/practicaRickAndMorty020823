package com.example.exampleretrofit.model.network

import com.example.exampleretrofit.core.RetrofitHelper
import com.example.exampleretrofit.model.data.CharacterListModel
import com.example.exampleretrofit.model.data.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharactersList(): CharacterListModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getCharactersList()
            response.body()
        }

    }

    suspend fun getCharacter(id: Int): CharacterModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getCharacter(id)
            response.body()
        }

    }
}