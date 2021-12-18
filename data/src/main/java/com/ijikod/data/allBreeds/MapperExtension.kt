package com.ijikod.data.allBreeds

import com.ijikod.data.allBreeds.entities.AllBreedsEntity
import com.ijikod.domain.allBreeds.entity.AllBreeds

fun AllBreedsApiContract.AllBreedsResponse.toAllBreedsEntity(): AllBreedsEntity {
    return AllBreedsEntity(
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