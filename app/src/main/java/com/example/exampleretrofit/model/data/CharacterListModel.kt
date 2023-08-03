package com.example.exampleretrofit.model.data

import com.google.gson.annotations.SerializedName

data class CharacterListModel(
    val info: InfoModel,
    val results: List<CharacterModel>
)
