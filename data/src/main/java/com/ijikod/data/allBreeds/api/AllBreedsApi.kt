package com.ijikod.data.allBreeds.api

import com.ijikod.data.allBreeds.AllBreedsApiContract
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AllBreedsApi {

    @GET("breeds/list/all")
    fun getAllBreads(): Single<AllBreedsApiContract.AllBreedsResponse>

    @GET("breed/{breed}/images")
    fun getBreedDetails(@Path("breed") breed: String): Single<AllBreedsApiContract.BreedDetailsResponse>

    @GET("breed/{breed}/{subBreed}/images")
    fun getSubBreedDetails(@Path("breed") breed: String,
                           @Path("subBreed") subBreed: String): Single<AllBreedsApiContract.BreedDetailsResponse>
}