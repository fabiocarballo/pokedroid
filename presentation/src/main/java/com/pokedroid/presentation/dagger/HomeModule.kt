package com.pokedroid.presentation.dagger

import com.petertackage.kotlinoptions.Option
import com.petertackage.kotlinoptions.optionOf
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemons
import com.pokedroid.domain.model.Pokemon
import com.pokedroid.domain.repository.PokemonList
import com.pokedroid.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Observable

@Module
class HomeModule {

    @Provides
    fun provideRetrievePokemons(repository: PokemonRepository): RetrievePokemons {
        return RetrievePokemons(repository)
    }

    @Provides
    fun provideRetrieveLocations(): RetrieveLocations {
        return RetrieveLocations()
    }

    // Fake Implementation for now
    @Provides
    fun providePokemonRepository(): PokemonRepository {
        return object: PokemonRepository {
            override fun pokemonsObservableStream(): Observable<Option<PokemonList>> {
                return Observable.just(optionOf(listOf(Pokemon("1", "Ilke"),
                                                       Pokemon("2", "Fabio"))))
            }

            override fun fetchPokemons(): Completable {
                return Completable.complete()
            }
        }
    }
}