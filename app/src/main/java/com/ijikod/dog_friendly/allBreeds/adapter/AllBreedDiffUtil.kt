package com.ijikod.dog_friendly.allBreeds.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsState
import com.ijikod.domain.allBreeds.entity.AllBreeds
import javax.inject.Inject

class AllBreedDiffUtil @Inject constructor() : DiffUtil.ItemCallback<AllBreedsState.Data>() {
    override fun areItemsTheSame(
        oldItem: AllBreedsState.Data,
        newItem: AllBreedsState.Data
    ) = oldItem.breeds == oldItem.breeds

    override fun areContentsTheSame(
        oldItem: AllBreedsState.Data,
        newItem: AllBreedsState.Data
    ) = oldItem.breeds == newItem.breeds
}