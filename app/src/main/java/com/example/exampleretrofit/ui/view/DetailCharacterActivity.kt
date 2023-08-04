package com.example.exampleretrofit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exampleretrofit.R
import com.example.exampleretrofit.databinding.ActivityDetailBinding
import com.example.exampleretrofit.model.data.CharacterModel
import com.example.exampleretrofit.model.network.CharacterService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private val api = CharacterService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        val id: Int = intent.getIntExtra(EXTRA_ID, 0)

        getCharacter(id)

        binding.btnBack.setOnClickListener { goBack() }
    }

    private fun getCharacter(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val character = api.getCharacter(id)
            character?.let {

                runOnUiThread {
                    render(it)
                }

            }
        }
    }

    private fun render(character: CharacterModel) {
        Picasso.get().load(character.image).into(binding.ivPhoto)

        binding.tvNameCharacter.text = character.name
        binding.tvGenderCharacter.text = character.gender

        if(character.status.equals("Alive")) {
            binding.statusCharacter.setImageResource(R.drawable.baseline_circle_green)
        } else {
            binding.statusCharacter.setImageResource(R.drawable.baseline_circle_red)
        }

        binding.tvLocationCharacter.text = character.location.name
        binding.tvOriginCharacter.text = character.origin.name
        binding.tvSpeciesCharacter.text = character.species
    }

    private fun goBack() {
        finish()
    }
}