package com.pegbeer.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pegbeer.domain.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather:Weather):Long

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<Weather>>

}