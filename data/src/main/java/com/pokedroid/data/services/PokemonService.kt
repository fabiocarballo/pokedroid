package com.pokedroid.data.services

import com.pokedroid.data.model.LocationRaw
import com.pokedroid.data.model.PokemonListRaw
import com.pokedroid.data.model.PokemonRaw
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("/api/v2/pokemon/")
    fun getPokemonURLs(): Observable<PokemonListRaw>

    @GET("/api/v2/pokemon/{id}/")
    fun getPokemon(@Path("id") id: Int): Observable<PokemonRaw>

    @GET("/api/v2/location/{id}/")
    fun getLocations(@Path("id") id: Int): Observable<List<LocationRaw>>
}