package com.ijikod.domain.allBreeds.repository

import io.reactivex.Completable

interface GetAllBreedsRepository {

    fun getAllBreeds(): Completable
}