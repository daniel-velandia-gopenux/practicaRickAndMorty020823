package com.example.exampleretrofit.model.data

import com.google.gson.annotations.SerializedName

data class CharacterListModel(
    var info: InfoModel,
    var results: MutableList<CharacterModel>
)
