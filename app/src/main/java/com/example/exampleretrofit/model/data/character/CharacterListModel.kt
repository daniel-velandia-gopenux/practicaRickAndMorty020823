package com.example.exampleretrofit.model.data.character

data class CharacterListModel(
    var info: InfoModel,
    var results: MutableList<CharacterModel>
)
