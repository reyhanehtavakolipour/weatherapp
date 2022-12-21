package rtp.reyhanehtpour.weatherapp.data.local

import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity

interface WeatherLocalDataSource {

    suspend fun getWeather(city: String, country: String?): Result<WeatherEntity>

    suspend fun saveWeather(weather: WeatherEntity)

    suspend fun getCities(city: String): Result<List<CityEntity>>

    suspend fun getAllCities(): Result<List<CityEntity>>

    suspend fun saveCities(cities: List<CityEntity>)

}