package com.pokedroid.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemons
import com.pokedroid.domain.model.Location
import com.pokedroid.domain.repository.PokemonList
import io.reactivex.Observable
import io.reactivex.Observable.zip
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction

class PokedexViewModel(private val retrievePokemons: RetrievePokemons, private val retrieveLocations: RetrieveLocations) : ViewModel() {

    val pokedexLiveData = MutableLiveData<PokedexScreenState>()

    private val compositeDisposable = CompositeDisposable()

    fun onBind() {
        compositeDisposable.add(bindPokemons())
    }

    private fun bindPokemons(): Disposable {
        val retrievedPokemons = retrievePokemons.retrieveBehaviorStream(Unit)
        val retrievedLocations = retrieveLocations.retrieveBehaviorStream(Unit)

        val pokedexObservable: Observable<PokedexScreenState> =
                zip(retrievedPokemons, retrievedLocations, BiFunction { pokeList: PokemonList, locList: List<Location> -> listOf(pokeList, locList) })
                        .map { PokedexScreenState.Data(it.size, it.size) }

        return pokedexObservable
                .startWith(PokedexScreenState.Loading)
                .onErrorReturn { PokedexScreenState.Error("Ups!") }
                .subscribe(pokedexLiveData::postValue, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        pokedexLiveData.postValue(PokedexScreenState.Error("Yuasdjas"))
    }

}