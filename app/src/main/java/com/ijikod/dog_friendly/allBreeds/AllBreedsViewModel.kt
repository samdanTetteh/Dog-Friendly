package com.ijikod.dog_friendly.allBreeds

import com.ijikod.dog_friendly.allBreeds.state.AllBreedsState
import com.ijikod.domain.allBreeds.useCase.AllBreedsUseCase
import com.ijikod.dog_friendly.common.RxViewModelStore
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AllBreedsViewModel @Inject constructor (
    useCase: AllBreedsUseCase
): RxViewModelStore<AllBreedsState, AllBreedsViewModel.AllBreedsEvent>(AllBreedsState()) {

    init {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .subscribe {

            }
            .addDisposable()
    }



    sealed class AllBreedsEvent {
        data class showBreed (val breed: String): AllBreedsEvent()
        data class showSubBreed (val subBreed: String): AllBreedsEvent()
    }
}