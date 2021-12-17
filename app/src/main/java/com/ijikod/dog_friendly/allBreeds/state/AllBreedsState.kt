package com.ijikod.dog_friendly.allBreeds.state

import com.ijikod.dog_friendly.common.AsyncResult

data class AllBreedsState(
    val showBreed: AsyncResult<Nothing>? = null,
    val showSubBreed: AsyncResult<Nothing>? = null
)