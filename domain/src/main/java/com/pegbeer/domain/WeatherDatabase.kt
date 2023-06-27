package com.pegbeer.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.Weather

@Database(entities = [
    Weather::class,
    City::class
], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract val dao:WeatherDao
}