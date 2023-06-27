package com.pegbeer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val main:String,
    val icon:String
)