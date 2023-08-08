package com.example.exampleretrofit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.exampleretrofit.R
import com.example.exampleretrofit.databinding.ActivityDetailBinding
import com.example.exampleretrofit.ui.viewModel.DetailCharacterViewModel
import com.squareup.picasso.Picasso

class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val id: Int = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.getCharacter(id)
        render()

        binding.btnBack.setOnClickListener { goBack() }
    }

    private fun render() {
        viewModel.character.observe(this, Observer {
            Picasso.get().load(it.image).into(binding.ivPhoto)

            binding.tvNameCharacter.text = it.name
            binding.tvGenderCharacter.text = it.gender

            val img = when(it.status) {
                "Alive" -> R.drawable.baseline_circle_green
                "Dead" -> R.drawable.baseline_circle_red
                else -> R.drawable.baseline_circle_gray
            }
            binding.statusCharacter.setImageResource(img as Int)

            binding.tvLocationCharacter.text = it.location.name
            binding.tvOriginCharacter.text = it.origin.name
            binding.tvSpeciesCharacter.text = it.species
        })
    }

    private fun goBack() {
        finish()
    }
}