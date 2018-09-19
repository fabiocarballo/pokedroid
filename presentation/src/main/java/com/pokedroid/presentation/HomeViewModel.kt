package com.pokedroid.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemons
import com.pokedroid.domain.model.Location
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.Observable.zip
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val retrievePokemons: RetrievePokemons,
                                        private val retrieveLocations: RetrieveLocations)
    : ViewModel() {

    val pokedexLiveData = MutableLiveData<PokedexScreenState>()

    private val compositeDisposable = CompositeDisposable()

    fun onBind() {
        compositeDisposable.add(bindPokemons())
    }

    private fun bindPokemons(): Disposable {
        val retrievedPokemons = retrievePokemons.retrieveBehaviorStream(Unit)
        val retrievedLocations = retrieveLocations.retrieveBehaviorStream(Unit)

        val pokedexObservable: Observable<PokedexScreenState> =
                zip(retrievedPokemons, retrievedLocations, BiFunction { pokeList: List<Pokemon>, locationList: List<Location> ->
                    PokedexScreenState.Data(pokeList, locationList)
                })

        return pokedexObservable
                .startWith(PokedexScreenState.Loading)
                .onErrorReturn { PokedexScreenState.Error("Ups!") }
                .subscribe(pokedexLiveData::postValue, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        pokedexLiveData.postValue(PokedexScreenState.Error("Yuasdjas"))
    }

}