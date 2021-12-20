package com.ijikod.dog_friendly

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ijikod.dog_friendly.allBreeds.fragments.AllBreedsFragment
import com.ijikod.dog_friendly.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class FragmentNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }


    @Test
    fun test_navigation_to_details_fragment() {
       launchFragmentInHiltContainer<AllBreedsFragment> {
           scrollAtAndCheckTestVisible(0, "affenpinscher")
        }
    }

    private fun scrollAtAndCheckTestVisible(position: Int, text: String) {
        onView(withId(R.id.all_breeds_list))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(withText(text)).check(matches(isDisplayed()))
    }
}