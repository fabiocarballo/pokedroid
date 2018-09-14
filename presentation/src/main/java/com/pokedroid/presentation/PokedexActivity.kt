package com.pokedroid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pokedroid.presentation.dagger.DaggerPokedexComponent
import javax.inject.Inject

class PokedexActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModel: PokedexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerPokedexComponent
            .builder()
            .build()
            .inject(this)

        viewModel.onBind()
    }
}