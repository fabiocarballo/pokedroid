package com.pokedroid.presentation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.pokedroid.domain.interactors.RetrieveLocations
import com.pokedroid.domain.interactors.RetrievePokemons
import com.pokedroid.domain.model.Location
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class PokedexViewModelTest {

    val retrieveLocations: RetrieveLocations = mock()
    val retrievePokemons: RetrievePokemons = mock()

    val tested = PokedexViewModel(retrievePokemons, retrieveLocations)

    @Test
    fun `correct data will be output - happy path`() {
        // setting up the mocking
        val expectedLocations = listOf(Location(1, "asdas"))
        val expectedPokemons = listOf(Pokemon("1", "asdas"), Pokemon("1", "asdas"))
        whenever(retrieveLocations.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedLocations))
        whenever(retrievePokemons.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedPokemons))

        // test
        tested.onBind()

        // assertion
        val liveDataValue = tested.pokedexLiveData.value
        assertEquals(PokedexScreenState.Data(expectedPokemons.size, expectedLocations.size), liveDataValue)
    }

    //@Test
    // if retrieve pokemons fails, what happens
    // if retrieve location fails, what happens
    // if both fail
}