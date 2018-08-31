package com.pokedroid.domain.repository

import com.petertackage.kotlinoptions.Option
import io.reactivex.Completable
import io.reactivex.Observable

interface PokemonRepository {

    fun pokemonsObservableStream(): Observable<Option<PokemonList>>

    fun fetchPokemons(): Completable
}