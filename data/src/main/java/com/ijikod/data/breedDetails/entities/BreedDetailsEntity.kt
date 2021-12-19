package com.ijikod.data.breedDetails.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breed_details")
data class BreedDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "status") val status: String,
    @SerializedName("message")
    @Expose
    val message: List<String>
)