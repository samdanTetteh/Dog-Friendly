package com.ijikod.dog_friendly.vm

import com.ijikod.dog_friendly.FakeRepository
import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.di.AppModule
import com.ijikod.domain.allBreeds.repository.GetAllBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [AppModule::class]
)
@Module
class FakeViewModel {

    @Provides
    fun getRepository(): GetAllBreedsRepository = FakeRepository()
}