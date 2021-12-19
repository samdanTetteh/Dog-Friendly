package com.ijikod.data.allBreeds

import com.ijikod.data.allBreeds.entities.AllBreedsEntity
import com.ijikod.data.breedDetails.entities.BreedDetailsEntity
import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails

fun AllBreedsApiContract.AllBreedsResponse.toEntity(): AllBreedsEntity {
    return AllBreedsEntity(
        status = this.status,
        message = this.message
    )
}

fun AllBreedsApiContract.BreedDetailsResponse.toEntity(breed: String): BreedDetailsEntity {
    return BreedDetailsEntity(
        breed = breed,
        status = this.status,
        message = this.message
    )
}


fun AllBreedsEntity.toDomain(): AllBreeds {
    return AllBreeds(
        status = this.status,
        message = this.message
    )
}

fun BreedDetailsEntity.toDomain(): BreedDetails {
    return BreedDetails(
        status = this.status,
        message = this.message
    )
}