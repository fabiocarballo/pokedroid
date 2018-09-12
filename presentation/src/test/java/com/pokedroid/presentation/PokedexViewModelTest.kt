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

    private val retrieveLocations: RetrieveLocations = mock()
    private val retrievePokemons: RetrievePokemons = mock()
    private val tested = PokedexViewModel(retrievePokemons, retrieveLocations)
    private val liveDataValue = tested.pokedexLiveData.value
    private val throwable = Throwable()

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
        assertEquals(PokedexScreenState.Data(expectedPokemons.size, expectedLocations.size), liveDataValue)
    }


    @Test
    fun `error will be shown - retrieve pokemons fails`() {
        // setting up the mocking
        //Approach 1: Directly return error instead of valid expectedPokemons
        val expectedLocations = listOf(Location(1, "asdas"))
        whenever(retrieveLocations.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedLocations))
        whenever(retrievePokemons.retrieveBehaviorStream(Unit)).thenReturn(Observable.error(throwable))

        // test
        tested.onBind()

        // assertion
        assertEquals((PokedexScreenState.Error("Ups!")), liveDataValue)

    }

    @Test
    fun `error will be shown - retrieve locations fails`() {
        // setting up the mocking
        // Approach 2: Made expectedLocations null so that it would throw error during onBind() call
        val expectedLocations = null
        val expectedPokemons = listOf(Pokemon("1", "asdas"), Pokemon("1", "asdas"))
        whenever(retrieveLocations.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedLocations))
        whenever(retrievePokemons.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedPokemons))

        // test
        tested.onBind()

        // assertion
        assertEquals((PokedexScreenState.Error("Ups!")), liveDataValue)
    }

    @Test
    fun `error will be shown - retrieve pokemons & locations  fail`() {
        // setting up the mocking
        val expectedLocations = null
        val expectedPokemons = null
        whenever(retrieveLocations.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedLocations))
        whenever(retrievePokemons.retrieveBehaviorStream(Unit)).thenReturn(Observable.just(expectedPokemons))

        // test
        tested.onBind()

        // assertion
        // I'm a bit confused about the error messages here, not sure which one would be thrown for these cases
        assertEquals((PokedexScreenState.Error("Yuasdjas")), liveDataValue)
    }

}