package com.pokedroid.domain.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.petertackage.kotlinoptions.None
import com.petertackage.kotlinoptions.Option
import com.petertackage.kotlinoptions.optionOf
import com.pokedroid.domain.repository.PokemonList
import com.pokedroid.domain.repository.PokemonRepository
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RetrievePokemonsTest {

    private val repository: PokemonRepository = mock()
    private val tested = RetrievePokemons(repository)

    private var testObserver = TestObserver<PokemonList>()

    private val pokemonsBehaviorStream: PublishSubject<Option<PokemonList>> = PublishSubject.create()

    @BeforeEach
    fun setUp() {
        whenever(repository.pokemonsObservableStream()).thenReturn(pokemonsBehaviorStream)

        tested.retrieveBehaviorStream(Unit).subscribe(testObserver)
    }

    @Nested
    @DisplayName("when pokemons are present")
    inner class SpaceDetailsIsPresent {

        private val spaceDetailsDomainEntity: PokemonList = mock()

        @BeforeEach
        fun setup() {
            pokemonsBehaviorStream.onNext(optionOf(spaceDetailsDomainEntity))
        }

        @Test
        fun `it should emit the pokemons`() {
            testObserver.assertValue(spaceDetailsDomainEntity)
        }
    }

    @Nested
    @DisplayName("when pokemons are not present")
    inner class SpaceDetailsIsNotPresent {

        private val spaceFetchSubject: PublishSubject<Any> = PublishSubject.create()

        @BeforeEach
        fun setup() {
            whenever(repository.fetchPokemons()).thenReturn(spaceFetchSubject.ignoreElements())

            pokemonsBehaviorStream.onNext(None)
        }

        @Test
        fun `it should fetch the pokemons`() {
            verify(repository).fetchPokemons()
        }

        @Nested
        @DisplayName("when pokemons fetch fails")
        inner class FetchFails {

            private val throwable = Throwable()

            @BeforeEach
            fun setup() {
                spaceFetchSubject.onError(throwable)
            }

            @Test
            fun `it should return the error`() {
                testObserver.assertError(throwable)
            }
        }

        @Nested
        @DisplayName("when pokemons fetch succeeds")
        inner class FetchSucceeds {

            @BeforeEach
            fun setup() {
                spaceFetchSubject.onComplete()
            }

            @Test
            fun `it should emit nothing nor complete`() {
                testObserver
                    .assertNoValues()
                    .assertNotComplete()
                    .assertNoErrors()
            }
        }
    }
}