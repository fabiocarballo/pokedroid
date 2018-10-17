package com.pokedroid.domain.interactors

import com.pokedroid.data.model.PokemonListRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.domain.interactors.base.RetrieveInteractor
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RetrieveFirstNPokemons(private val pokemonService: PokemonService) : RetrieveInteractor<Int, List<Pokemon>> {

    override fun retrieveBehaviorStream(params: Int): Observable<List<Pokemon>> {
        return pokemonService.getPokemonURLs()
                .subscribeOn(Schedulers.io())
                .map { convertPokemon(it, params) }
    }

    private fun convertPokemon(pokemonListRaw: PokemonListRaw, limit: Int): List<Pokemon> {
        return pokemonListRaw.results
                .take(limit)
                .mapIndexed { index, pokemonURLsRaw -> Pokemon(index+1, pokemonURLsRaw.name) }
    }
}