package com.pokedroid.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedroid.domain.interactors.RetrievePokemons
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class PokedexViewModel(private val retrievePokemons: RetrievePokemons): ViewModel() {

    val pokedexLiveData = MutableLiveData<PokedexScreenState>()

    private val compositeDisposable = CompositeDisposable()

    fun onBind() {
        compositeDisposable.add(bindPokemons())
    }

    private fun bindPokemons(): Disposable {
        val pokedexObservable: Observable<PokedexScreenState> =
            retrievePokemons
                .retrieveBehaviorStream(Unit)
                .map { PokedexScreenState.Data(it.size) }

        return pokedexObservable
            .startWith(PokedexScreenState.Loading)
            .onErrorReturn { PokedexScreenState.Error("Ups!") }
            .subscribe(pokedexLiveData::postValue, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        pokedexLiveData.postValue(PokedexScreenState.Error("Yuasdjas"))
    }

}