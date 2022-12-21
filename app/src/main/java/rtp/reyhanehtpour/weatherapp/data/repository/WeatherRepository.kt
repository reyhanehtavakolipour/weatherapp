package rtp.reyhanehtpour.weatherapp.data.repository


import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.local.WeatherLocalDataSource
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity
import rtp.reyhanehtpour.weatherapp.data.remote.WeatherRemoteDataSource
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote

import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSourceImpl: WeatherLocalDataSource
) {

    suspend fun getRemoteWeather(query: String): Result<WeatherRemote>{
        return weatherRemoteDataSource.getWeather(query)
    }

    suspend fun getRemoteCities(query: String): Result<List<CityRemote>>{
        return weatherRemoteDataSource.getCities(query)
    }

    suspend fun getLocalWeather(city: String, country: String?): Result<WeatherEntity>{
        return weatherLocalDataSourceImpl.getWeather(city, country)
    }

    suspend fun getLocalCities(city: String): Result<List<CityEntity>>{
        return weatherLocalDataSourceImpl.getCities(city)
    }

    suspend fun saveWeather(weather: WeatherEntity){
        weatherLocalDataSourceImpl.saveWeather(weather)
    }

    suspend fun saveCities(cities: List<CityEntity>){
        weatherLocalDataSourceImpl.saveCities(cities)
    }

}