package com.pokedroid

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pokedroid.helpers.JsonFileHelper
import com.pokedroid.presentation.HomeActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityMockWebServer {

    @get:Rule
    var rule = ActivityTestRule(HomeActivity::class.java)

    private lateinit var mockServer: MockWebServer

    private val jsonFileHelper = JsonFileHelper()

    @Before
    @Throws
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @Test
    fun testPokemonUrls() {
        val jsonFile = "mock.json"

        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(jsonFileHelper.getStringFromFile(getInstrumentation().context, jsonFile))

        mockServer.enqueue(mockResponse)

        Espresso.onView(ViewMatchers.withId(R.id.button_id)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.pokemon_item)).check(matches(withText("mockName")))
    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }

}