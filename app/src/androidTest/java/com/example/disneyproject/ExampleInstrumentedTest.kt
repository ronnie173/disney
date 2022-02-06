package com.example.disneyproject

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.disneyproject.ui.home.HomeAdapter
import junit.framework.TestCase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import timber.log.Timber
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : TestCase() {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.disneyproject", appContext.packageName)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_if_recyclerview_is_displayed() {
        onView(withId(R.id.home_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_click_on_cardView() {
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.comicTitle)).check(matches(withText("Spider-Bot Infinity Comic (2021) #9")))
    }

    @Test
    fun testBackNavigationToHome() {
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.comicTitle)).check(matches(withText("Spider-Bot Infinity Comic (2021) #9")))

        pressBack()
        onView(withId(R.id.home_recycler_view)).check(matches(isDisplayed()))

    }
}