package com.ijikod.dog_friendly

import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.common.RxJavaTestRule
import com.ijikod.domain.allBreeds.useCase.AllBreedsUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AllBreedsViewModelTest {

    @get:Rule
    val rxRule = RxJavaTestRule()

    private lateinit var viewModel: AllBreedsViewModel


    @Before
    fun setUp() {
        viewModel = AllBreedsViewModel(AllBreedsUseCase(FakeRepository()))
    }


    @Test
    fun `initial states values are null`() {
        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.allBreeds == null }
        testObserver.assertValue { state -> state.breedDetails == null }
        testObserver.assertValue { state -> state.showSubBreed == null }
    }

    @Test
    fun `when init is called data for allBreeds is loaded`() {
        viewModel.init()

        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.getAllBreeds == Fakes.getFakeAllBreeds() }
    }

    @Test
    fun `when showBreedDetails is called images breeds are loaded`() {
        viewModel.onShowBreedDetails("shepherd", String())

        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.getBreedDetails == Fakes.getFakeBreedDetails().message }
    }
}