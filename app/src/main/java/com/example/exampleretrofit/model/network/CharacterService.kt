package com.example.exampleretrofit.model.network

import com.example.exampleretrofit.core.RetrofitHelper
import com.example.exampleretrofit.model.data.character.CharacterListModel
import com.example.exampleretrofit.model.data.character.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit().create(CharacterApiClient::class.java)

    suspend fun getCharactersList(page: Int = 1): CharacterListModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getCharactersList(page)
            response.body()
        }

    }

    suspend fun getCharacter(id: Int): CharacterModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getCharacter(id)
            response.body()
        }

    }
}