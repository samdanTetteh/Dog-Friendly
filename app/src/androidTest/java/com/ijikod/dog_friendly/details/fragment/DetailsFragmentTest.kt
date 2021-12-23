package com.ijikod.dog_friendly.details.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ijikod.dog_friendly.R
import com.ijikod.dog_friendly.atPosition
import com.ijikod.dog_friendly.common.BREED_ARG
import com.ijikod.dog_friendly.common.SUB_BREED_ARG
import com.ijikod.dog_friendly.common.TEST_BREED
import com.ijikod.dog_friendly.common.TEST_SUB_BREED
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
class DetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Test
    fun givenArgumentsArePassedToDetailsFragmentToolBarIsShowingSelectedBreed() {
        val fragmentArgs = bundleOf(BREED_ARG to TEST_BREED,
            SUB_BREED_ARG to TEST_SUB_BREED)

        launchDetailsFragment(fragmentArgs)
        Espresso.onView(withId(R.id.toolbar))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed(),
                        ViewMatchers.hasDescendant(ViewMatchers.withText(TEST_SUB_BREED))
                    )
                )
            )
    }

    @Test
    fun givenArgumentsArePassedToDetailsFragmentRecyclerViewIsDisplayed() {
        val fragmentArgs = bundleOf(BREED_ARG to TEST_BREED,
            SUB_BREED_ARG to TEST_SUB_BREED)

        launchDetailsFragment(fragmentArgs)
        Espresso.onView(withId(R.id.breed_details_list))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed()
                    )
                )
            )
    }

    @Test
    fun givenDetailsFragmentRecyclerViewIsDisplayedIsViewHolderCorrect() {
        val fragmentArgs = bundleOf(BREED_ARG to TEST_BREED,
            SUB_BREED_ARG to TEST_SUB_BREED)

        launchDetailsFragment(fragmentArgs)
        Espresso.onView(withId(R.id.breed_details_list))
            .check(
                ViewAssertions.matches(
                    atPosition(0, withId(R.id.details_image))
                )
            )
    }




    private fun launchDetailsFragment(args: Bundle) {
        launchFragmentInHiltContainer<DetailsFragment>(args) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.detailsFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}