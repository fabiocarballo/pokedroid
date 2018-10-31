package com.pokedroid.presentation.dagger

import com.pokedroid.data.services.PokemonService
import com.pokedroid.data.services.RetrofitClientInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitClientInstance().retrofitClientInstance()
    }

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}