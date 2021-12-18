package com.ijikod.dog_friendly.allBreeds

import android.util.Log
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsState
import com.ijikod.dog_friendly.common.AsyncResult
import com.ijikod.dog_friendly.common.Reducer
import com.ijikod.domain.allBreeds.useCase.AllBreedsUseCase
import com.ijikod.dog_friendly.common.RxViewModelStore
import com.ijikod.dog_friendly.common.mapToAsyncResult
import com.ijikod.domain.allBreeds.entity.AllBreeds
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AllBreedsViewModel @Inject constructor (
    useCase: AllBreedsUseCase
): RxViewModelStore<AllBreedsState, AllBreedsViewModel.AllBreedsEvent>(AllBreedsState()) {

    init {

        useCase.getAllBreeds()
            .mapToAsyncResult()
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                applyState(Reducer { it.copy(allBreeds = result) })
            }.addDisposable()


        useCase.invoke()
            .mapToAsyncResult()
            .subscribeOn(Schedulers.io())
            .subscribe{
                if (it is AsyncResult.Error){
                    publish(AllBreedsEvent.Error(it.error))
                }
            }.addDisposable()



    }



    sealed class AllBreedsEvent {
        data class showBreed (val breed: String): AllBreedsEvent()
        data class showSubBreed (val subBreed: String): AllBreedsEvent()
        data class Error (val error: Throwable): AllBreedsEvent()
    }
}