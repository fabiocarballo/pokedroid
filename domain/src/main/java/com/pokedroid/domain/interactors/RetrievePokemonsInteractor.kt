package com.pokedroid.domain.interactors

import com.petertackage.kotlinoptions.None
import com.petertackage.kotlinoptions.Option
import com.petertackage.kotlinoptions.filterNotNone
import com.pokedroid.domain.interactors.base.RetrieveInteractor
import com.pokedroid.domain.repository.PokemonList
import com.pokedroid.domain.repository.PokemonRepository
import io.reactivex.Observable

class RetrievePokemonsInteractor(private val repository: PokemonRepository): RetrieveInteractor<Unit, PokemonList> {

    override fun retrieveBehaviorStream(params: Unit): Observable<PokemonList> {
        return repository
            .pokemonsObservableStream()
            .flatMap { fetchIfRepositoryIsEmpty(it) }
            .filterNotNone()
    }

    private fun fetchIfRepositoryIsEmpty(entityOption: Option<PokemonList>): Observable<Option<PokemonList>> =
        entityOption.match({ Observable.just(entityOption) }, { fetchPokemons() })

    private fun fetchPokemons(): Observable<Option<PokemonList>> =
        repository
            .fetchPokemons()
            .andThen(Observable.just<Option<PokemonList>>(None))
}