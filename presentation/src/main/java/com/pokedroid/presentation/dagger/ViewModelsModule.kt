package com.pokedroid.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.pokedroid.presentation.PokedexViewModel
import com.pokedroid.presentation.viewmodel.ViewModelFactory
import com.pokedroid.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokedexViewModel::class)
    internal abstract fun postListViewModel(viewModel: PokedexViewModel): PokedexViewModel
}