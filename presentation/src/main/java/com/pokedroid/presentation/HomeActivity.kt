package com.pokedroid.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.pokedroid.presentation.dagger.DaggerHomeComponent
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var pokemonListView: RecyclerView
    private lateinit var locationListView: RecyclerView
    private lateinit var pokemonViewManager: RecyclerView.LayoutManager
    private lateinit var locationViewManager: RecyclerView.LayoutManager
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent
                .builder()
                .build()
                .inject(this)

        viewModel.onBind()
        observerPokemons()
        observeLocations()
    }

    private fun observerPokemons() {
        pokemonViewManager = LinearLayoutManager(this)

        viewModel.pokedexLiveData.observe(this, Observer<PokedexScreenState?> {
            when (it) {
                is PokedexScreenState.Data -> {
                    pokemonListView = findViewById<RecyclerView>(R.id.pokemon_list).apply {
                        setHasFixedSize(true)
                        layoutManager = pokemonViewManager
                        pokemonAdapter = PokemonAdapter(it.pokemonList)
                        adapter = pokemonAdapter

                    }
                }
            }
        })
    }

    private fun observeLocations() {
        locationViewManager = LinearLayoutManager(this)

        viewModel.pokedexLiveData.observe(this, Observer<PokedexScreenState?> {
            when (it) {
                is PokedexScreenState.Data -> {
                    locationListView = findViewById<RecyclerView>(R.id.location_list).apply {
                        setHasFixedSize(true)
                        layoutManager = locationViewManager
                        locationAdapter = LocationAdapter(it.locationList)
                        adapter = locationAdapter
                    }
                }
            }
        })
    }
}