package com.pokedroid

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pokedroid.data.model.PokemonListRaw
import com.pokedroid.data.services.PokemonService
import com.pokedroid.presentation.HomeActivity
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@RunWith(AndroidJUnit4::class)
class HomeActivityMockWebServer {

    @get:Rule
    var rule = ActivityTestRule(HomeActivity::class.java)

    lateinit var mockServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        // Initialize mock webserver
        mockServer = MockWebServer()
        // Start the local server
        mockServer.start(8080)


    }

    @Test
    fun testPokemonUrls() {
        val json = """
            { "results": [{ "name": "mockName", "url": "mockUrl" }]}
        """.trimIndent()

        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(json)

        mockServer.enqueue(mockResponse)

        Espresso.onView(ViewMatchers.withId(R.id.button_id)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.pokemon_item)).check(matches(withText("mockName")))

    }


    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }

    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }


}