package com.pokedroid.presentation.dagger

import com.pokedroid.presentation.HomeActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelsModule::class, HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)

    @Subcomponent.Builder
    interface Builder {
        fun plus(module: HomeModule): Builder
        fun build(): HomeComponent
    }
}