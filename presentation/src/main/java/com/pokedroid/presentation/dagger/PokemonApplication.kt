package com.pokedroid.presentation.dagger

import android.app.Application

open class PokemonApplication: Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeAppComponent()
    }

    open fun initializeAppComponent() {
        appComponent = buildAppComponent()
    }

    open fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent
                .builder()
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }
}