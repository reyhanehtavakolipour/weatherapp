package rtp.reyhanehtpour.weatherapp.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import rtp.reyhanehtpour.weatherapp.data.*
import rtp.reyhanehtpour.weatherapp.data.ErrorHandling.getErrorModel
import rtp.reyhanehtpour.weatherapp.data.mapper.asWeather
import rtp.reyhanehtpour.weatherapp.data.mapper.asWeatherEntity
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.data.model.Weather
import rtp.reyhanehtpour.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

open class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    ioDispatcher: CoroutineDispatcher
): UseCase<City, Weather, RequestType>(ioDispatcher) {

    private val TAG = "GetWeatherUseCase"

    /**
     * - Get weather data from api
     * - Then save it  to local db
     * - Last, call localExecute function to get data from local db
     */
    override suspend fun remoteExecute(parameters: City): Result<Weather> {
        val query = "${parameters.cityName},${parameters.country}"
        val remoteResult =  weatherRepository.getRemoteWeather(query)
        return if (remoteResult.succeeded){
            val weather = remoteResult.data
            weatherRepository.saveWeather(weather.asWeatherEntity())
            localExecute(parameters)
        }else{
            (remoteResult as Result.Error).getErrorModel(TAG)
        }
    }

    /**
     * Get weather data from local db.
     */
    override suspend fun localExecute(parameters: City): Result<Weather> {
        val localListResult = weatherRepository.getLocalWeather(parameters.cityName, parameters.country)
        return when{
             localListResult.succeeded -> Result.Success(data = localListResult.data.asWeather())
             localListResult.failed -> (localListResult as Result.Error).getErrorModel(TAG)
            else -> Result.EmptyResult
        }
    }

}