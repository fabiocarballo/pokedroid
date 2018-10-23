package com.pokedroid.domain.interactors

import com.pokedroid.data.model.PokemonRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.domain.interactors.base.RetrieveInteractor
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RetrievePokemon(private val pokemonService: PokemonService) : RetrieveInteractor<Int, Pokemon> {

    override fun retrieveBehaviorStream(params: Int): Observable<Pokemon> {
        return pokemonService.getPokemon(params)
                .subscribeOn(Schedulers.io())
                .map { convertPokemonRaw(it) }
    }

    private fun convertPokemonRaw(pokemonRaw: PokemonRaw): Pokemon {
        return Pokemon(pokemonRaw.id, pokemonRaw.name)
    }
}