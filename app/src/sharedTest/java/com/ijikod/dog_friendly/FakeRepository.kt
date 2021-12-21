package com.ijikod.dog_friendly

import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FakeRepository @Inject constructor(): GetAllBreedsRepository {


    override fun loadAllBreeds(): Completable {
        return Completable.complete()
    }

    override fun getAllBreeds(): Observable<AllBreeds> {
        return Observable.just(Fakes.getFakeAllBreeds())
    }

    override fun loadBreedDetails(breed: String, subBreed: String): Completable {
        return Completable.complete()
    }

    override fun getBreedDetails(breed: String): Observable<BreedDetails>
            = Observable.just(Fakes.getFakeBreedDetails())


}