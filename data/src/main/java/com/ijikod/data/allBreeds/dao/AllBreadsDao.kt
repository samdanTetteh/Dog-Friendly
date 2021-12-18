package com.ijikod.data.allBreeds.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ijikod.data.allBreeds.entities.AllBreedsEntity
import com.ijikod.data.common.EntityDao
import io.reactivex.Observable

@Dao
abstract class AllBreadsDao: EntityDao<AllBreedsEntity> {

    @Transaction
    @Query("SELECT * FROM all_breeds")
    abstract fun getAllBreeds(): Observable<AllBreedsEntity>

    @Query("DELETE FROM all_breeds")
    abstract fun deleteAllBreeds()

    fun deleteAndInsert(allBreedsEntity: AllBreedsEntity) {
        deleteAllBreeds()
        insertAll(allBreedsEntity)
    }

}