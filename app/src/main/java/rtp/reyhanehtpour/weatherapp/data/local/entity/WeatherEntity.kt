package rtp.reyhanehtpour.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    val id: String,
    val city: String,
    val country: String,
    val temp : String,
    val minTemp: String,
    val maxTemp: String,
    val description: String
)

