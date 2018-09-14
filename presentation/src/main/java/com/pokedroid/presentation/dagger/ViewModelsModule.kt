package com.pokedroid.presentation.dagger

import android.arch.lifecycle.ViewModelProvider
import com.pokedroid.presentation.HomeViewModel
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
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun postListViewModel(viewModel: HomeViewModel): HomeViewModel
}