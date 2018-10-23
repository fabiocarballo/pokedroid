package com.pokedroid

import com.pokedroid.presentation.dagger.ApplicationComponent
import com.pokedroid.presentation.dagger.DaggerApplicationComponent
import com.pokedroid.presentation.dagger.PokemonApplication

open class TestApplication : PokemonApplication() {

    override fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .serviceModule(TestServiceModule())
                .build()
    }

}