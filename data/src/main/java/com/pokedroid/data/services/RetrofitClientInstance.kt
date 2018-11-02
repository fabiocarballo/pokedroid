package com.pokedroid.data.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    val BASE_URL: String = "https://pokeapi.co"

    fun retrofitClientInstance(baseUrl: String = BASE_URL): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


}