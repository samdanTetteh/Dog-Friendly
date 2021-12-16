package com.ijikod.dog_friendly.allBreeds.state

import common.AsyncResult

data class AllBreedsState(
    val showBreed: AsyncResult<Nothing>? = null,
    val showSubBreed: AsyncResult<Nothing>? = null
)