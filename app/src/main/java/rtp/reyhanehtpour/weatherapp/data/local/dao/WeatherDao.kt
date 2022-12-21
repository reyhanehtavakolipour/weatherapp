package rtp.reyhanehtpour.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity


@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)


    @Query("select * from weather where city=:city and country=:country")
    suspend fun getWeatherWithCityAndCountry(city: String, country: String): List<WeatherEntity>?

    @Query("select * from weather where city=:city")
    suspend fun getWeather(city: String): List<WeatherEntity>?

    @Query("DELETE FROM weather")
    suspend fun deleteWeather()

}