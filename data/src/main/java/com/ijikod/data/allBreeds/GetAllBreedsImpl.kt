package com.ijikod.data.allBreeds

import com.ijikod.data.allBreeds.api.AllBreedsApi
import com.ijikod.data.allBreeds.dao.AllBreadsDao
import com.ijikod.data.breedDetails.dao.BreedDetailsDao
import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GetAllBreedsImpl @Inject constructor(
    private val remote: AllBreedsApi,
    private val allBreedsDao: AllBreadsDao,
    private val breedDetailsDao: BreedDetailsDao
) : GetAllBreedsRepository {

    override fun loadAllBreeds(): Completable {
        return remote.getAllBreads()
            .toObservable()
            .doAfterNext{
                allBreedsDao.deleteAndInsert(it.toEntity())
            }.ignoreElements()
    }

    override fun getAllBreeds(): Observable<AllBreeds> {
       return allBreedsDao.getAllBreeds().map {
           it.toDomain()
       }
    }

    override fun loadBreedDetails(breed: String, subBreed: String): Completable {
        return if (subBreed.isEmpty()) {
            remote.getBreedDetails(breed)
                .toObservable()
                .doAfterNext{
                    breedDetailsDao.insert(it.toEntity(breed))
                }.ignoreElements()
        } else {
            remote.getSubBreedDetails(breed, subBreed)
                .toObservable()
                .doAfterNext{
                    breedDetailsDao.insert(it.toEntity(subBreed))
                }.ignoreElements()
        }
    }


    override fun getBreedDetails(breed: String): Observable<BreedDetails> {
        return breedDetailsDao.getBreedDetails(breed).map {
            it.toDomain()
        }
    }
}