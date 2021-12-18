package com.ijikod.data.allBreeds

import com.ijikod.data.allBreeds.api.AllBreedsApi
import com.ijikod.data.allBreeds.dao.AllBreadsDao
import com.ijikod.data.common.DatabaseTransactionRunner
import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAllBreedsImpl @Inject constructor(
    private val remote: AllBreedsApi,
    private val dao: AllBreadsDao
) : GetAllBreedsRepository {

    override fun loadAllBreeds(): Completable {
        return remote.getAllBreads()
            .toObservable()
            .doAfterNext{
                dao.deleteAndInsert(it.toAllBreedsEntity())
            }.ignoreElements()
    }

    override fun getAllBreeds(): Observable<AllBreeds> {
       return dao.getAllBreeds().map {
           it.toDomain()
       }
    }
}