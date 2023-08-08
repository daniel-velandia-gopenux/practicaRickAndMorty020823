package com.example.exampleretrofit.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleretrofit.model.data.character.CharacterModel
import com.example.exampleretrofit.model.network.CharacterService
import kotlinx.coroutines.launch

class DetailCharacterViewModel: ViewModel() {

    val character = MutableLiveData<CharacterModel>()
    private val api = CharacterService()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            character.postValue(api.getCharacter(id))
        }
    }
}