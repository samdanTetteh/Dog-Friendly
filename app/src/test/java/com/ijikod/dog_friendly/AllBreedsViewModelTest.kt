package com.ijikod.dog_friendly

import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.common.RxJavaTestRule
import com.ijikod.dog_friendly.fakes.Fakes
import com.ijikod.domain.allBreeds.useCase.AllBreedsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AllBreedsViewModelTest {

    @get:Rule
    val rxRule = RxJavaTestRule()

    @MockK
    lateinit var allBreedsUseCase: AllBreedsUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        every { allBreedsUseCase.invoke()} returns Completable.complete()
        every { allBreedsUseCase.loadBreedDetails("australian", "shepherd") } returns Completable.complete()
    }


    private fun createViewModel(): AllBreedsViewModel {
        return AllBreedsViewModel(
            useCase = allBreedsUseCase
        )
    }


    @Test
    fun `initial states values are null`() {
        val viewModel = createViewModel()
        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.allBreeds == null }
        testObserver.assertValue { state -> state.breedDetails == null }
        testObserver.assertValue { state -> state.showSubBreed == null }
    }

    @Test
    fun `when init is called data for allBreeds is loaded`() {
        every { allBreedsUseCase.getAllBreeds() } returns  Observable.just(Fakes.getFakeAllBreeds())
        val viewModel = createViewModel()
        viewModel.init()

        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.getAllBreeds == Fakes.getFakeAllBreeds() }
    }

    @Test
    fun `when showBreedDetails is called images breeds are loaded`() {
        every { allBreedsUseCase.getBreedDetails("shepherd") } returns Observable.just(Fakes.getFakeBreedDetails())
        val viewModel = createViewModel()
        viewModel.onShowBreedDetails("shepherd", String())

        val testObserver = viewModel.states().test()
        testObserver.assertValue { state -> state.getBreedDetails == Fakes.getFakeBreedDetails().message }
    }
}