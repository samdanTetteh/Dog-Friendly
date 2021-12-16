package com.ijikod.data.allBreeds

import com.ijikod.data.allBreeds.api.AllBreedsApi
import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GetAllBreedsImpl @Inject constructor(
    private val remote: AllBreedsApi
) : GetAllBreedsRepository {


    override fun getAllBreeds(): Completable {
        return remote.getAllBreads()
            .toObservable()
            .doOnNext{

            }
            .ignoreElements()
    }

}