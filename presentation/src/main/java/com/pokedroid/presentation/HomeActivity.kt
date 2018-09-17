package com.pokedroid.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pokedroid.presentation.dagger.DaggerHomeComponent
import javax.inject.Inject

class HomeActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent
            .builder()
            .build()
            .inject(this)

        viewModel.onBind()
    }
}