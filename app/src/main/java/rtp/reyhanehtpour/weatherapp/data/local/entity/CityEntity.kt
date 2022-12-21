package rtp.reyhanehtpour.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long ?= null,
    val cityName: String,
    val country: String
)