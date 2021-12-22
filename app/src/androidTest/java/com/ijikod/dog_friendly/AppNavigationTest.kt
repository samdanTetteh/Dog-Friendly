package com.ijikod.dog_friendly

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ijikod.dog_friendly.allBreeds.fragments.AllBreedsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import junit.framework.Assert.assertNotNull


@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class AppNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Test
    fun givenApplicationIsLunchedNavigationIsCorrect() {
        // Launch fragment
        launchAllBreedsFragment()

        // Get current navigation
        val destination = navController.currentDestination

        assertEquals(destination?.id, R.id.allBreedsFragment)
    }


    @Test
    fun givenListItemIsClickedBreedDetailsIsDisplayed() {
        //Launch fragment
        launchAllBreedsFragment()

        //Click on first breed item
        onView(withId(R.id.all_breeds_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        //Check that it navigates to Detail screen
        val destination = navController.currentDestination
        assertEquals(destination?.id, R.id.detailsFragment)

    }


    private fun launchAllBreedsFragment() {
        launchFragmentInHiltContainer<AllBreedsFragment> {
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