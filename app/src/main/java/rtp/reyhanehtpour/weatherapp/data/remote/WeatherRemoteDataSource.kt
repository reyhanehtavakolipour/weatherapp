package rtp.reyhanehtpour.weatherapp.data.remote

import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote

interface WeatherRemoteDataSource {

     suspend fun getWeather(query: String): Result<WeatherRemote>

     suspend fun getCities(query: String): Result<List<CityRemote>>

}