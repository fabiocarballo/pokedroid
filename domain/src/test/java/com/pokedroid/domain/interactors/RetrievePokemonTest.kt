package com.pokedroid.domain.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.pokedroid.data.model.PokemonRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RetrievePokemonTest {

    private val service: PokemonService = mock()
    private val tested = RetrievePokemon(service)
    private var testObserver = TestObserver<Pokemon>()

    @BeforeEach
    fun setUp() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Pokemon is retrieved successfully`() {
        // arrange
        val pokemonRaw = PokemonRaw(1, "Ilke")

        whenever(service.getPokemon(1)).thenReturn(Observable.just(pokemonRaw))

        // act
        tested.retrieveBehaviorStream(1).subscribe(testObserver)

        // assert
        val expectedPokemon = Pokemon(1, "Ilke")
        testObserver.assertValue(expectedPokemon)
    }

    @Test
    fun `Error is thrown when no Pokemon retrieved`() {
        val throwable = Throwable()
        whenever(service.getPokemon(1)).thenReturn(Observable.error(throwable))

        tested.retrieveBehaviorStream(1).subscribe(testObserver)

        testObserver.assertError(throwable)
    }

}