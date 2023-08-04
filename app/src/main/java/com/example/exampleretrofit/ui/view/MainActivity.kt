package com.example.exampleretrofit.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exampleretrofit.adapter.CharacterAdapter
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.model.data.CharacterListModel
import com.example.exampleretrofit.model.network.CharacterService
import com.example.exampleretrofit.ui.view.DetailCharacterActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val api = CharacterService()
    private lateinit var characters: CharacterListModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        CoroutineScope(Dispatchers.IO).launch {
            val characters = api.getCharactersList()
            characters?.let {
                scrollNested()
                runOnUiThread {
                    initRecyclerView(it)
                }
            }
        }

    }

    private fun scrollNested() {
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
                v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                getNextPage()
            }
        })
    }

    private fun getNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            val nextPage = characters.info.next[characters.info.next.lastIndex].digitToInt()
            val characterNextList = api.getCharactersList(nextPage)
            runOnUiThread {
                characterNextList?.let {
                    addCharacters(it)
                }
            }
        }
    }

    private fun addCharacters(characterNextList: CharacterListModel) {
        characters.info = characterNextList.info
        val size = characters.results.size
        characters.results.addAll(characterNextList.results)
        adapter.notifyItemRangeInserted(size, characterNextList.results.size)

    }

    private fun initRecyclerView(characterList: CharacterListModel) {
        characters = characterList
        adapter = CharacterAdapter(characters.results) { onItemsSelect(it) }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun onItemsSelect(id: Int) {
        val intent = Intent(this, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}