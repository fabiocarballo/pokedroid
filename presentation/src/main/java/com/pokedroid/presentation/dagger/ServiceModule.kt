package com.pokedroid.presentation.dagger

import com.pokedroid.data.services.PokemonService
import com.pokedroid.data.services.RetrofitClientInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Provides
    open fun provideRetrofit(): Retrofit {
        return RetrofitClientInstance().retrofitClientInstance()
    }

    @Provides
    open fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}