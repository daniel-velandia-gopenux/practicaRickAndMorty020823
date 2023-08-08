package com.example.exampleretrofit.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exampleretrofit.adapter.CharacterAdapter
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.model.data.character.CharacterListModel
import com.example.exampleretrofit.ui.view.DetailCharacterActivity.Companion.EXTRA_ID
import com.example.exampleretrofit.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: CharacterAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.onCreate()
        initUI()
    }

    private fun initUI() {
        viewModel.characters.observe(this, Observer {
            initRecyclerView(it)
            infiniteScroll()
        })

        viewModel.sizeItems.observe(this, Observer {
            adapter.notifyItemRangeInserted(it.start, it.sizeItems)
        })
    }

    private fun initRecyclerView(characters: CharacterListModel) {
        adapter = CharacterAdapter(characters.results) { onItemsSelect(it) }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun onItemsSelect(id: Int) {
        val intent = Intent(this, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun infiniteScroll() {
        binding.nestedScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener {
                    v, scrollX, scrollY, oldScrollX, oldScrollY ->

                if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    viewModel.getNextPage()
                }
            })
    }
}