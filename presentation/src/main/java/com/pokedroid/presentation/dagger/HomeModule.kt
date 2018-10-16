package com.pokedroid.presentation.dagger

import com.petertackage.kotlinoptions.Option
import com.petertackage.kotlinoptions.optionOf
import com.pokedroid.data.services.PokemonService
import com.pokedroid.data.services.RetrofitClientInstance
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemon
import com.pokedroid.domain.interactors.RetrievePokemons
import com.pokedroid.domain.model.Pokemon
import com.pokedroid.domain.repository.PokemonList
import com.pokedroid.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Retrofit

@Module
class HomeModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitClientInstance().retrofitClientInstance()
    }

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

    @Provides
    fun provideRetrievePokemon(service: PokemonService): RetrievePokemon {
        return RetrievePokemon(service)
    }

    @Provides
    fun provideRetrieveLocations(): RetrieveLocations {
        return RetrieveLocations()
    }

    // Fake Implementation for now
    @Provides
    fun providePokemonRepository(): PokemonRepository {
        return object : PokemonRepository {
            override fun pokemonsObservableStream(): Observable<Option<PokemonList>> {
                return Observable.just(optionOf(listOf(Pokemon(1, "Ilke"),
                        Pokemon(2, "Fabio"))))
            }

            override fun fetchPokemons(): Completable {
                return Completable.complete()
            }
        }
    }
}