package com.ijikod.domain.allBreeds.useCase

import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AllBreedsUseCase @Inject constructor(private val repository: GetAllBreedsRepository) {
    operator fun invoke(): Completable  {
        return repository.loadAllBreeds()
    }

    fun getAllBreeds(): Observable<AllBreeds> {
        return repository.getAllBreeds()
    }

    fun loadBreedDetails(breed: String, subBreed:String): Completable {
        return repository.loadBreedDetails(breed, subBreed)
    }

    fun getBreedDetails(breed: String): Observable<BreedDetails> {
        return repository.getBreedDetails(breed)
    }
}