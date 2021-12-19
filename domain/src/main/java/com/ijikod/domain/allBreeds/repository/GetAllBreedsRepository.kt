package com.ijikod.domain.allBreeds.repository

import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails
import io.reactivex.Completable
import io.reactivex.Observable

interface GetAllBreedsRepository {

    fun loadAllBreeds(): Completable

    fun getAllBreeds(): Observable<AllBreeds>

    fun loadBreedDetails(breed: String, subBreed: String): Completable

    fun getBreedDetails(breed: String): Observable<BreedDetails>
}