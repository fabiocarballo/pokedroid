package com.pokedroid.domain.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.pokedroid.data.model.PokemonListRaw
import com.pokedroid.data.model.PokemonURLsRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class RetrieveFirstNPokemonsTest {

    private val service: PokemonService = mock()
    private val tested = RetrieveFirstNPokemons(service)
    private var testObserver = TestObserver<List<Pokemon>>()

    @BeforeEach
    fun setUp() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Pokemons are retrieved successfully`() {
        // arrange
        val pokemonUrlsList = listOf(
                PokemonURLsRaw("name", "url"),
                PokemonURLsRaw("name1", "url1"))

        val pokemonListRaw = PokemonListRaw(pokemonUrlsList)
        whenever(service.getPokemonURLs()).thenReturn(Observable.just(pokemonListRaw))

        // act
        tested.retrieveBehaviorStream(1).subscribe(testObserver)

        // assert
        val expectedPokemonList = listOf(Pokemon(1, "name"))
        testObserver.assertValue(expectedPokemonList)
    }

    @Test
    fun `Error is thrown when no Pokemons retrieved`() {
        val throwable = Throwable()
        whenever(service.getPokemonURLs()).thenReturn(Observable.error(throwable))

        tested.retrieveBehaviorStream(1).subscribe(testObserver)

        testObserver.assertError(throwable)
    }

    @Test
    fun `Error is thrown when limit is invalid`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            tested.retrieveBehaviorStream(-1).subscribe(testObserver)
        }
    }
}
