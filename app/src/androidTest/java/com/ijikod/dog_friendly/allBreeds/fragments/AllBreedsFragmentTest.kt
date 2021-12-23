package com.ijikod.dog_friendly.allBreeds.fragments

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ijikod.dog_friendly.R
import com.ijikod.dog_friendly.common.TEST_BREED
import com.ijikod.dog_friendly.first
import com.ijikod.dog_friendly.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class AllBreedsFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Test
    fun givenAllBreedsFragmentIsDisplayedIsRecyclerViewVisible() {
        launchAllBreedsFragment()
        Espresso.onView(first(withId(R.id.all_breeds_list))).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun givenAllBreedsFragmentIsDisplayedIsFirstBreedItemCorrect() {
        launchAllBreedsFragment()
        Espresso.onView(first(withId(R.id.all_breeds_list)))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        hasDescendant(withId(R.id.breed_txt).also {
                            it.matches(withText(TEST_BREED))
                        }),
                    )
                )
            )}


    private fun launchAllBreedsFragment() {
        launchFragmentInHiltContainer<AllBreedsFragment>() {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.allBreedsFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}