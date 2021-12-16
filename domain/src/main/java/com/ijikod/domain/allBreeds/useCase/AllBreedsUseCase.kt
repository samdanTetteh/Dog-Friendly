package com.ijikod.domain.allBreeds.useCase

import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import javax.inject.Inject

class AllBreedsUseCase @Inject constructor(private val repository: GetAllBreedsRepository) {
    operator fun invoke(): Completable  {
        return repository.getAllBreeds()
    }
}