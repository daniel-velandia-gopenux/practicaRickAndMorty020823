package com.example.exampleretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleretrofit.databinding.ItemCharacterRecyclerViewBinding
import com.example.exampleretrofit.model.data.CharacterModel

class CharacterAdapter(
    private val characters: List<CharacterModel>,
    private val onItemSelect: (Int) -> Unit) : RecyclerView.Adapter<CharacterHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = ItemCharacterRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item = characters[position]
        holder.render(item, onItemSelect)
    }
}