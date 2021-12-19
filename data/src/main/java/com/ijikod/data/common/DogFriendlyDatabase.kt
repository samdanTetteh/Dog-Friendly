package com.ijikod.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijikod.data.allBreeds.dao.AllBreadsDao
import com.ijikod.data.allBreeds.dao.StringListMapConverter
import com.ijikod.data.allBreeds.entities.AllBreedsEntity
import com.ijikod.data.breedDetails.dao.BreedDetailsDao
import com.ijikod.data.breedDetails.entities.BreedDetailsEntity


@TypeConverters(StringListMapConverter::class)
@Database(entities = [AllBreedsEntity::class, BreedDetailsEntity::class], version = 3)
abstract class DogFriendlyDatabase: RoomDatabase() {
    abstract fun allBreedsDao(): AllBreadsDao
    abstract fun breedDetailsDao(): BreedDetailsDao
}