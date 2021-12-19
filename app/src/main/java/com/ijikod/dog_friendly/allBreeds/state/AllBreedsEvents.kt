package com.ijikod.dog_friendly.allBreeds.state


sealed class AllBreedsEvents {
    object Loading: AllBreedsEvents()
    data class ShowAllBreeds(val state: AllBreedsStates): AllBreedsEvents()
    data class ShowBreedDetails (val selectedBreed: String, val state: AllBreedsStates): AllBreedsEvents()
    data class Error (val error: Throwable): AllBreedsEvents()
}