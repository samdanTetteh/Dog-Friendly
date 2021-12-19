package com.ijikod.data.allBreeds

import com.google.gson.annotations.SerializedName

interface AllBreedsApiContract {

    data class AllBreedsResponse(
        @SerializedName("message") val message: Map<String, List<String>>,
        @SerializedName("status") val status: String
    )

    data class BreedDetailsResponse(
        @SerializedName("message") val message: List<String>,
        @SerializedName("status") val status: String
    )

}