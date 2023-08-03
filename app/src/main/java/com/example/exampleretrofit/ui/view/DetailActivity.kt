package com.example.exampleretrofit.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exampleretrofit.R
import com.example.exampleretrofit.databinding.ActivityDetailBinding
import com.example.exampleretrofit.extencions.loadUrl
import com.example.exampleretrofit.model.data.CharacterModel
import com.example.exampleretrofit.model.network.CharacterService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

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
                    Picasso.get().load(character.image).into(binding.ivPhoto)

                    binding.tvName.text = character.name
                    binding.tvGender.text = "Gender: " + character.gender
                    binding.tvStatus.text = "Status: " + character.status
                    binding.tvLocation.text = "Last location: " + character.location.name
                    binding.tvOrigin.text = "Origin: " + character.origin.name
                    binding.tvSpecies.text = "Species: " + character.species
                }

            } ?: run { }
        }
    }

    private fun goBack() {
        finish()
    }
}