package com.example.exampleretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleretrofit.databinding.ItemCharacterRecyclerViewBinding
import com.example.exampleretrofit.model.data.CharacterModel

class CharacterRecyclerViewAdapter(
    private val characters: List<CharacterModel>,
    private val onItemSelect: (Int) -> Unit) : RecyclerView.Adapter<CharacterRecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterRecyclerViewHolder {
        val binding = ItemCharacterRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterRecyclerViewHolder, position: Int) {
        val item = characters[position]
        holder.render(item, onItemSelect)
    }
}