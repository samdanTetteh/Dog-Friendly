package com.ijikod.dog_friendly.allBreeds.state

import com.ijikod.dog_friendly.common.AsyncResult
import com.ijikod.domain.allBreeds.entity.AllBreeds

data class AllBreedsState(
    val allBreeds: AsyncResult<AllBreeds>? = null,
    val showSubBreed: AsyncResult<Nothing>? = null)
{
    val isLoading: Boolean
        get() = allBreeds is AsyncResult.Loading

    val getAllBreeds: AllBreeds?
        get() = if (allBreeds is AsyncResult.Success && allBreeds.data != null) {
            allBreeds.data
        } else null
}