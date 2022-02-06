package com.example.disneyproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.disneyproject.ui.home.HomeAdapter
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeFragmentInstrumentedTest : TestCase() {
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
        Thread.sleep(3000)
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(3000)

    }

    @Test
    fun test_back_navigation_to_home() {
        Thread.sleep(3000)
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(2000)

        pressBack()
        Thread.sleep(3000)

        onView(withId(R.id.home_recycler_view)).check(matches(isDisplayed()))

    }

    @Test
    fun test_read_now_button() {
        Thread.sleep(3000)
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(3000)
        onView(withId(R.id.readNowBtn)).perform(click())

    }
}