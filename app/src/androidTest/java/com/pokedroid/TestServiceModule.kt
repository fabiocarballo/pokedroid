package com.pokedroid

import com.pokedroid.data.services.PokemonService
import com.pokedroid.presentation.dagger.ServiceModule
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import retrofit2.Retrofit

@Module
class TestServiceModule: ServiceModule() {

    private val myService: PokemonService = mockk()

    @Provides
    override fun providePokemonService(retrofit: Retrofit): PokemonService {
        return myService
    }
}