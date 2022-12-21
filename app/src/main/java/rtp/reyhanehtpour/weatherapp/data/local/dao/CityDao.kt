package rtp.reyhanehtpour.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM city WHERE cityName LIKE '%' || :cityValue || '%'")
    suspend fun getCities(cityValue: String): List<CityEntity>?

    @Query("select * from city WHERE cityName=:city and country=:country")
    suspend fun getCity(city: String, country: String): CityEntity?

    @Query("select * from city")
    suspend fun getCities(): List<CityEntity>?
}