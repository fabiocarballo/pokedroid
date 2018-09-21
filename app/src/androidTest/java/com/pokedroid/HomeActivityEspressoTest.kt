package com.pokedroid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.pokedroid.presentation.HomeActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityEspressoTest {

    @get:Rule
    var rule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testListTitles() {
        onView(withId(R.id.pokemons_title)).check(matches(isDisplayed()))
        onView(withId(R.id.locations_title)).check(matches(isDisplayed()))
    }
}
