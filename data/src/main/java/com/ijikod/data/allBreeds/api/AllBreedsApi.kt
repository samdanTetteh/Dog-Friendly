package com.ijikod.data.allBreeds.api

import com.ijikod.data.allBreeds.AllBreedsApiContract
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface AllBreedsApi {

    @GET("/breeds/list/all")
    fun getAllBreads(): Single<AllBreedsApiContract.allBreedsResponse>
}