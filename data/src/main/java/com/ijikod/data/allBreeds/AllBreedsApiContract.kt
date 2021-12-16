package com.ijikod.data.allBreeds

import com.google.gson.annotations.SerializedName

interface AllBreedsApiContract {

    data class allBreedsResponse(
        @SerializedName("message") val message: Map<String, List<String>>,
        @SerializedName("status") val status: String
    )

}