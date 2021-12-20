package com.ijikod.dog_friendly.allBreeds

import com.ijikod.dog_friendly.allBreeds.state.AllBreedsEvents
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsStates
import com.ijikod.dog_friendly.common.AsyncResult
import com.ijikod.dog_friendly.common.Reducer
import com.ijikod.domain.allBreeds.useCase.AllBreedsUseCase
import com.ijikod.dog_friendly.common.RxViewModelStore
import com.ijikod.dog_friendly.common.mapToAsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AllBreedsViewModel @Inject constructor (
    private val useCase: AllBreedsUseCase
): RxViewModelStore<AllBreedsStates, AllBreedsEvents>(AllBreedsStates()) {


   fun init() {
        useCase.getAllBreeds()
            .mapToAsyncResult()
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                if (result is AsyncResult.Error){
                    publish(AllBreedsEvents.Error(result.error))
                } else {
                    applyState(Reducer { currentState().copy(allBreeds = result) })
                    publish(AllBreedsEvents.ShowAllBreeds(currentState()))
                }
            }.addDisposable()


        useCase.invoke()
            .mapToAsyncResult()
            .subscribeOn(Schedulers.io())
            .subscribe{
                if (it is AsyncResult.Error){
                    publish(AllBreedsEvents.Error(it.error))
                }
            }.addDisposable()

    }



    fun onShowBreedDetails(breed: String, subBreed: String) {
            useCase.getBreedDetails(breed)
                .mapToAsyncResult()
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    if(result is AsyncResult.Error){
                        publish(AllBreedsEvents.Error(result.error))
                    } else {
                        applyState(Reducer { currentState().copy(breedDetails = result) })
                        publish(AllBreedsEvents.ShowBreedDetails(breed, currentState()))
                    }
                }.addDisposable()


            useCase.loadBreedDetails(breed, subBreed)
                .mapToAsyncResult()
                .doOnNext {
                    publish(AllBreedsEvents.Loading)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it is AsyncResult.Error) {
                        publish(AllBreedsEvents.Error(it.error))
                    }
                }.addDisposable()
        }
}