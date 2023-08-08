package com.example.exampleretrofit.model.data.character

data class CharacterModel(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: OriginModel,
    var location: LocationModel,
    var image: String,
    var episode: List<String>,
    var url: String,
    var created: String
)
