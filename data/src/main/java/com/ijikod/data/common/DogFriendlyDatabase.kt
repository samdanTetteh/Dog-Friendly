package com.ijikod.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijikod.data.allBreeds.dao.AllBreadsDao
import com.ijikod.data.allBreeds.dao.StringListMapConverter
import com.ijikod.data.allBreeds.entities.AllBreedsEntity


@TypeConverters(StringListMapConverter::class)
@Database(entities = [AllBreedsEntity::class], version = 1)
abstract class DogFriendlyDatabase: RoomDatabase() {
    abstract fun allBreedsDao(): AllBreadsDao
}