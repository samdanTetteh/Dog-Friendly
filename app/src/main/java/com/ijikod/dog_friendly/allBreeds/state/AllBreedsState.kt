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


    val breedsData: List<RecyclerViewItem>
        get() = if (allBreeds is AsyncResult.Success && allBreeds.data != null) {
            val list = arrayListOf<RecyclerViewItem>()
            for (map in allBreeds.data.message) {
                list.add(SectionItem(title = map.key))
                for (value in map.value) {
                    list.add(ContentItem(value, dataSize = map.value.size))
                }
            }
            list
        } else emptyList()

     inner class Data {
        val breeds = getAllBreeds?.message
    }



    open class RecyclerViewItem
    class SectionItem(val title: String) : RecyclerViewItem()
    class ContentItem(val name: String, val dataSize: Int) : RecyclerViewItem()
}