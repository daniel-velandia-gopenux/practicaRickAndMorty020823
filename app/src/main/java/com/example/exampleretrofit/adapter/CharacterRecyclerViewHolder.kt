package com.example.exampleretrofit.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.exampleretrofit.databinding.ItemCharacterRecyclerViewBinding
import com.example.exampleretrofit.model.data.CharacterModel
import com.squareup.picasso.Picasso

class CharacterRecyclerViewHolder(
    private val binding: ItemCharacterRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root){

    fun render(characterModel: CharacterModel, onItemSelect: (Int) -> Unit) {

        Picasso.get().load(characterModel.image).into(binding.ivPhoto)

        binding.tvName.text = characterModel.name

        itemView.setOnClickListener { onItemSelect(characterModel.id) }
    }
}