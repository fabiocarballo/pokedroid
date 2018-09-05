package com.pokedroid.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemons
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 1. Integrate with the new RetrieveLocations and pass that data into the screenState. The end goal will
 * be to present two horizontal lists in the home screen (one with locations, one with pokemons)
 */
class PokedexViewModel(private val retrievePokemons: RetrievePokemons, private val retrieveLocations: RetrieveLocations) : ViewModel() {

    val pokedexLiveData = MutableLiveData<PokedexScreenState>()

    private val compositeDisposable = CompositeDisposable()

    fun onBind() {
        compositeDisposable.add(bindPokemons())
        compositeDisposable.add(bindLocations())
    }

    private fun bindPokemons(): Disposable {
        val pokedexObservable: Observable<PokedexScreenState> =
                retrievePokemons
                        .retrieveBehaviorStream(Unit)
                        .map {
                            PokedexScreenState.Data(it.size)
                        }

        return pokedexObservable
                .startWith(PokedexScreenState.Loading)
                .onErrorReturn { PokedexScreenState.Error("Ups!") }
                .subscribe(pokedexLiveData::postValue, ::handleError)
    }

    private fun bindLocations(): Disposable {
        val pokedexObservable: Observable<PokedexScreenState> =
                retrieveLocations
                        .retrieveBehaviorStream(Unit)
                        .map {
                            PokedexScreenState.LocationData(it.size)
                        }
        return pokedexObservable
                .startWith(PokedexScreenState.Loading)
                .onErrorReturn { PokedexScreenState.Error("No locations for you!") }
                .subscribe(pokedexLiveData::postValue, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        pokedexLiveData.postValue(PokedexScreenState.Error("Yuasdjas"))
    }

}