package com.example.exampleretrofit.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exampleretrofit.adapter.CharacterRecyclerViewAdapter
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.model.data.CharacterModel
import com.example.exampleretrofit.model.network.CharacterService
import com.example.exampleretrofit.ui.view.DetailActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val api = CharacterService()

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
                Log.i("dogs", it.results.size.toString())
                runOnUiThread {
                    initRecyclerView(it.results)
                }
            }
        }

    }

    private fun initRecyclerView(characters: List<CharacterModel>) {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = CharacterRecyclerViewAdapter(characters) { onItemsSelect(it) }
    }

    private fun onItemsSelect(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}