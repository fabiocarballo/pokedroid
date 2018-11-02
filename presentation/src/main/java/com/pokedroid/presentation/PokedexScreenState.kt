package com.pokedroid.presentation

import com.pokedroid.domain.model.Location
import com.pokedroid.domain.model.Pokemon

sealed class PokedexScreenState {

    data class Data(val pokemonList: List<Pokemon>,
                    val locationList: List<Location>) : PokedexScreenState()

    object Error : PokedexScreenState()
    object Loading : PokedexScreenState()

}