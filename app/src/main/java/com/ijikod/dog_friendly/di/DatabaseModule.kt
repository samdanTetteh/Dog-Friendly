package com.ijikod.dog_friendly.di

import android.content.Context
import androidx.room.Room
import com.ijikod.data.allBreeds.dao.AllBreadsDao
import com.ijikod.data.breedDetails.dao.BreedDetailsDao
import com.ijikod.data.common.DogFriendlyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

   @Provides
   fun provideAllBreedsDao(dogFriendlyDatabase: DogFriendlyDatabase): AllBreadsDao = dogFriendlyDatabase.allBreedsDao()

    @Provides
    fun provideBreedDetailsDao(dogFriendlyDatabase: DogFriendlyDatabase): BreedDetailsDao = dogFriendlyDatabase.breedDetailsDao()

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): DogFriendlyDatabase {
        return Room.databaseBuilder(appContext, DogFriendlyDatabase::class.java, "Dog-Friendly")
            .fallbackToDestructiveMigration()
            .build()
    }

}