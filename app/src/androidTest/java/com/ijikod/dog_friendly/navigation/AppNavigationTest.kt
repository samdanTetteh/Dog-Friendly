package com.ijikod.dog_friendly.navigation

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ijikod.dog_friendly.R
import com.ijikod.dog_friendly.allBreeds.fragments.AllBreedsFragment
import com.ijikod.dog_friendly.allBreeds.fragments.AllBreedsFragmentDirections
import com.ijikod.dog_friendly.details.fragment.DetailsFragment
import com.ijikod.dog_friendly.first
import com.ijikod.dog_friendly.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify


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
    fun givenListItemIsClickedBreedDetailsNavigationIsDisplayed() {
        var currentFragment: Fragment? = null
      launchFragmentInHiltContainer<AllBreedsFragment>() {
          currentFragment = this
            navController.setGraph(R.navigation.nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

        }

        onView(first(withId(R.id.all_breeds_list)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        currentFragment?.findNavController()?.currentDestination?.let { currentDestination ->
            assertEquals(currentDestination.id, R.id.detailsFragment)
        }
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