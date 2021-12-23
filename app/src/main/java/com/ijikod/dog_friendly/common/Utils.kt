package com.ijikod.dog_friendly.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val BASE_URL = "https://dog.ceo/api/"

const val VIEW_TYPE_SECTION = 1
const val VIEW_TYPE_ITEM = 2

const val BREED_ARG = "breed"
const val SUB_BREED_ARG = "subBreed"

const val TEST_BREED = "australian"
const val TEST_SUB_BREED = "shepherd"


fun RecyclerView.autoFitColumns(columnWidth: Int) {
    val displayMetrics = this.context.resources.displayMetrics
    val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
    this.layoutManager = GridLayoutManager(this.context, noOfColumns)
}