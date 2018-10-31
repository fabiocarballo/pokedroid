package com.pokedroid

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pokedroid.data.model.PokemonListRaw
import com.pokedroid.data.model.PokemonURLsRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.presentation.HomeActivity
import io.mockk.every
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityMocking {

    @get:Rule
    var rule = ActivityTestRule(HomeActivity::class.java)

    private lateinit var pokemonService: PokemonService

    private var mockedObservable: PokemonListRaw = PokemonListRaw(
            listOf(PokemonURLsRaw("ilke","urlofilke"),PokemonURLsRaw("fabio","urloffabio")))

    @Before
    fun setup() {
        val testApplication = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        pokemonService = testApplication.getApplicationComponent().pokemonService()

        every { pokemonService.getPokemonURLs() }.returns(Observable.just(mockedObservable))
    }

    @Test
    fun testListTitles() {
        Espresso.onView(ViewMatchers.withId(R.id.button_id)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.pokemon_list)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(2)))
        Espresso.onView(ViewMatchers.withId(R.id.pokemons_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.locations_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}