package rtp.reyhanehtpour.weatherapp.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import rtp.reyhanehtpour.weatherapp.data.*
import rtp.reyhanehtpour.weatherapp.data.ErrorHandling.getErrorModel
import rtp.reyhanehtpour.weatherapp.data.mapper.asCities
import rtp.reyhanehtpour.weatherapp.data.mapper.asCitiesEntity
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject


open class GetCitiesUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    ioDispatcher: CoroutineDispatcher
): UseCase<String, List<City>, RequestType>(ioDispatcher) {

    private val TAG = "GetWeatherUseCase"

    /**
     * - Get cities data from api
     * - Then save it  to local db
     * - Last, call localExecute function to get data from local db
     */
    override suspend fun remoteExecute(parameters: String): Result<List<City>> {
        val remoteResult =  weatherRepository.getRemoteCities(parameters)
        return if (remoteResult.succeeded){
            val cities = remoteResult.data
            weatherRepository.saveCities(cities.asCitiesEntity())
            localExecute(parameters)
        }else{
            (remoteResult as Result.Error).getErrorModel(TAG)
        }
    }

    /**
     * Get cities data from local db.
     */
    override suspend fun localExecute(parameters: String): Result<List<City>> {
        val localListResult = weatherRepository.getLocalCities(parameters)
        return when{
            localListResult.succeeded -> Result.Success(data = localListResult.data.asCities())
            localListResult.failed -> (localListResult as Result.Error).getErrorModel(TAG)
            else -> Result.EmptyResult
        }
    }

}