package com.pegbeer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id:Int,
    val name:String,
    val state:String,
    val country:String
)
