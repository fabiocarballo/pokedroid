package com.pokedroid.presentation.dagger

import dagger.Component

@Component(modules = [ServiceModule::class])
interface ApplicationComponent {

    fun inject(application: PokemonApplication)

    fun homeComponentBuilder(): HomeComponent.Builder
}
