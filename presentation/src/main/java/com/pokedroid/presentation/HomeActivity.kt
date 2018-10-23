package com.pokedroid.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.pokedroid.presentation.dagger.PokemonApplication
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val pokemonAdapter = PokemonAdapter()
    private val locationAdapter = LocationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        (application as PokemonApplication).getApplicationComponent()
                .homeComponentBuilder()
                .build()
                .inject(this)

        setRecyclerView()
        observeThings()

        findViewById<Button>(R.id.button_id).setOnClickListener { viewModel.onBind() }

    }

    private fun setRecyclerView() {
        val pokemonRecyclerView = findViewById<RecyclerView>(R.id.pokemon_list)
        pokemonRecyclerView.setHasFixedSize(true)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
        pokemonRecyclerView.adapter = pokemonAdapter


        findViewById<RecyclerView>(R.id.location_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = locationAdapter
        }
    }

    private fun observeThings() {
        viewModel.pokedexLiveData
                .observe(this, Observer<PokedexScreenState?> {
                    when (it) {
                        is PokedexScreenState.Data -> {
                            pokemonAdapter.pokemonList = it.pokemonList
                            pokemonAdapter.notifyDataSetChanged()

                            locationAdapter.locationList = it.locationList
                            locationAdapter.notifyDataSetChanged()
                        }
                    }
                })
    }
}