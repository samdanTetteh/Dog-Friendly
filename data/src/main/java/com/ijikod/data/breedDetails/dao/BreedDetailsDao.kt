package com.ijikod.data.breedDetails.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ijikod.data.breedDetails.entities.BreedDetailsEntity
import com.ijikod.data.common.EntityDao
import io.reactivex.Observable

@Dao
abstract class BreedDetailsDao: EntityDao<BreedDetailsEntity> {

    @Transaction
    @Query("SELECT * FROM breed_details where breed = :breed")
    abstract fun getBreedDetails(breed: String): Observable<BreedDetailsEntity>

}