package com.pegbeer.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Entity(tableName = "favorites")
@Serializable
data class Weather(
    @Transient
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long,

    @SerialName("last_updated")
    val lastUpdated: String,

    @SerialName("temp_c")
    val tempC: Double,

    @SerialName("temp_f")
    val tempF: Double,

    @SerialName("is_day")
    val isDay: Long,

    @Ignore
    val condition: WeatherCondition,

    @SerialName("wind_mph")
    val windMph: Double,

    @SerialName("wind_kph")
    val windKph: Double,

    @SerialName("wind_degree")
    val windDegree: Long,

    @SerialName("wind_dir")
    val windDir: String,

    @SerialName("pressure_mb")
    val pressureMB: Long,

    @SerialName("pressure_in")
    val pressureIn: Double,

    @SerialName("precip_mm")
    val precipMm: Long,

    @SerialName("precip_in")
    val precipIn: Long,

    val humidity: Long,
    val cloud: Long,

    @SerialName("feelslike_c")
    val feelslikeC: Double,

    @SerialName("feelslike_f")
    val feelslikeF: Double,

    @SerialName("vis_km")
    val visKM: Long,

    @SerialName("vis_miles")
    val visMiles: Long,

    val uv: Long,

    @SerialName("gust_mph")
    val gustMph: Double,

    @SerialName("gust_kph")
    val gustKph: Double
) {
    constructor(
        id: Int,
        lastUpdatedEpoch: Long,
        lastUpdated: String,
        tempC: Double,
        tempF: Double,
        isDay: Long,
        windMph: Double,
        windKph: Double,
        windDegree: Long,
        windDir: String,
        pressureMB: Long,
        pressureIn: Double,
        precipMm: Long,
        precipIn: Long,
        humidity: Long,
        cloud: Long,
        feelslikeC: Double,
        feelslikeF: Double,
        visKM: Long,
        visMiles: Long,
        uv: Long,
        gustMph: Double,
        gustKph: Double
    ) : this(
        id,
        lastUpdatedEpoch,
        lastUpdated,
        tempC,
        tempF,
        isDay,
        WeatherCondition("","",0L),
        windMph,
        windKph,
        windDegree,
        windDir,
        pressureMB,
        pressureIn,
        precipMm,
        precipIn,
        humidity,
        cloud,
        feelslikeC,
        feelslikeF,
        visKM,
        visMiles,
        uv,
        gustMph,
        gustKph
    )
}
