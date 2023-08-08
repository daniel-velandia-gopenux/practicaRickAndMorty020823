package com.example.exampleretrofit.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleretrofit.model.data.character.CharacterListModel
import com.example.exampleretrofit.model.data.utils.SizeItems
import com.example.exampleretrofit.model.network.CharacterService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var characters = MutableLiveData<CharacterListModel>()
    var sizeItems = MutableLiveData<SizeItems>()
    private val api = CharacterService()

    fun onCreate() {
        viewModelScope.launch {
            characters.postValue(api.getCharactersList())
        }
    }

    fun getNextPage() {
        viewModelScope.launch {
            characters.value?.let {
                val nextPage = it.info.next[it.info.next.lastIndex].digitToInt()
                val nextCharacters = api.getCharactersList(nextPage)
                addCharacters(nextCharacters)
            }
        }
    }

    private fun addCharacters(nextCharacters: CharacterListModel?) {
        nextCharacters?.let {
            var size = characters.value!!.results.size
            characters.value!!.results.addAll(it.results)
            characters.postValue(CharacterListModel(it.info, characters.value!!.results))

            sizeItems.postValue(SizeItems(size, it.results.size))
        }
    }
}