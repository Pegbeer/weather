package com.pegbeer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Location (
    val id: Long,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
){
    override fun toString(): String {
        return String.format("%s, %s", name, country)
    }
}
