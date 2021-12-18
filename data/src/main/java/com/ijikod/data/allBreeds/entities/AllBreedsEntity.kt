package com.ijikod.data.allBreeds.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "all_breeds")
data class AllBreedsEntity(
    @PrimaryKey
    @ColumnInfo(name = "status") val status: String,
    @SerializedName("message")
    @Expose
    val message: Map<String, List<String>>
)