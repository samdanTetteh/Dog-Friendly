package com.ijikod.dog_friendly.allBreeds.state

import androidx.compose.runtime.Composable
import com.ijikod.dog_friendly.common.AsyncResult
import com.ijikod.domain.allBreeds.entity.AllBreeds
import com.ijikod.domain.allBreeds.entity.BreedDetails

data class AllBreedsStates(
    val allBreeds: AsyncResult<AllBreeds>? = null,
    val breedDetails: AsyncResult<BreedDetails>? = null,
    val showSubBreed: AsyncResult<Nothing>? = null)
{
    val isLoading: Boolean
        get() = allBreeds is AsyncResult.Loading

    val getAllBreeds: AllBreeds?
        get() = if (allBreeds is AsyncResult.Success && allBreeds.data != null) {
            allBreeds.data
        } else null


    val getBreedDetails: List<String>?
        get() =  if (breedDetails is AsyncResult.Success && breedDetails.data != null) {
            breedDetails.data.message
        } else null


    val allBreedsFormattedData: List<RecyclerViewItem>
        get() = if (allBreeds is AsyncResult.Success && allBreeds.data != null) {
            val list = arrayListOf<RecyclerViewItem>()
            for (map in allBreeds.data.message) {
                list.add(SectionItem(breed = map.key))
                for (value in map.value) {
                    list.add(ContentItem(breed = map.key, subBreed = value))
                }
            }
            list
        } else emptyList()


    open class RecyclerViewItem
    class SectionItem(val breed: String) : RecyclerViewItem()
    class ContentItem(val breed: String, val subBreed: String) : RecyclerViewItem()
}