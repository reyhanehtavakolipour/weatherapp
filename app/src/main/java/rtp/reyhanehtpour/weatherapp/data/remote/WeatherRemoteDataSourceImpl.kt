package rtp.reyhanehtpour.weatherapp.data.remote

import rtp.reyhanehtpour.weatherapp.data.RequestError
import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote
import rtp.reyhanehtpour.weatherapp.data.remote.service.WeatherService
import rtp.reyhanehtpour.weatherapp.getCitiesUrl
import rtp.reyhanehtpour.weatherapp.getWeatherUrl

class WeatherRemoteDataSourceImpl(val weatherService: WeatherService) : WeatherRemoteDataSource {

    override suspend fun getWeather(query: String): Result<WeatherRemote> {
            val response=  weatherService.getWeather(getWeatherUrl(query))
            if (response.isSuccessful){
                val body = response.body()
                if (body != null) {
                     return Result.Success(body)
                }
            }
        return Result.Error(
            RequestError(
            code = response.code(),
            message = response.message()
         )
        )
    }

    override suspend fun getCities(query: String): Result<List<CityRemote>> {
        val response=  weatherService.getCities(url = getCitiesUrl(query))
        if (response.isSuccessful){
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            RequestError(
                code = response.code(),
                message = response.message()
            )
        )
    }
}