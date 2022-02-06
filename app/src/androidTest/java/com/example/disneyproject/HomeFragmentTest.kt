package com.example.disneyproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.disneyproject.models.Result
import com.example.disneyproject.ui.home.HomeAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    val LIST_ITEM_IN_TEST = 3
//    val RESULT_IN_TEST = Result()

    /**
     * Recyclerview comes into view
     */


    /**
     * Select a list item, and nav to details fragment
     * and back nav
     */
    @Test
    fun test_if_home_fragment_is_visible() {
        onView(withId(R.id.home_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(LIST_ITEM_IN_TEST,
                    ViewActions.click()
                ))

        onView(withId(R.id.comicTitle)).check(matches(withText("Shang-Chi:Brothers & Sisters Infinity Comic (2021) #4")))
    }
    /**
     * select list item, nav to backpress
     */

    /**
     * select list item, nav to Details Fragment
     * select read more button
     */

    /**
     * select list item, nav to Details Fragment
     * select read more button
     */
}