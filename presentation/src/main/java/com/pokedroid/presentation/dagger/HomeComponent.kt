package com.pokedroid.presentation.dagger

import com.pokedroid.presentation.HomeActivity
import dagger.Component

@Component(modules = [ViewModelsModule::class, HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)
}