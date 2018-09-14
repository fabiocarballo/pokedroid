package com.pokedroid.presentation.dagger

import com.pokedroid.presentation.PokedexActivity
import dagger.Component

@Component(modules = [ViewModelsModule::class, PokedexModule::class])
interface PokedexComponent {

    fun inject(activity: PokedexActivity)
}