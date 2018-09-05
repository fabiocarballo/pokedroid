package com.pokedroid.presentation

sealed class PokedexScreenState {

    data class Data(val numPokemons: Int): PokedexScreenState()
    data class LocationData(val numLocations: Int): PokedexScreenState()
    data class Error(val message: String): PokedexScreenState()
    object Loading: PokedexScreenState()

}