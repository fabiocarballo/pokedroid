package com.pokedroid.presentation.dagger

import com.pokedroid.data.services.PokemonService
import dagger.Component

@Component(modules = [ServiceModule::class])
interface ApplicationComponent {

    fun inject(application: PokemonApplication)

    fun pokemonService(): PokemonService

    fun homeComponentBuilder(): HomeComponent.Builder
}
