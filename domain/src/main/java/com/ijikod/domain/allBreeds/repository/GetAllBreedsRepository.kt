package com.ijikod.domain.allBreeds.repository

import com.ijikod.domain.allBreeds.entity.AllBreeds
import io.reactivex.Completable
import io.reactivex.Observable

interface GetAllBreedsRepository {

    fun loadAllBreeds(): Completable

    fun getAllBreeds(): Observable<AllBreeds>
}