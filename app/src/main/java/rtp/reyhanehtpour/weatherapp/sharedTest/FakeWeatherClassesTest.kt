package rtp.reyhanehtpour.weatherapp.sharedTest

import rtp.reyhanehtpour.weatherapp.data.RequestError
import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.local.WeatherLocalDataSource
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity
import rtp.reyhanehtpour.weatherapp.data.mapper.asWeatherEntity
import rtp.reyhanehtpour.weatherapp.data.remote.WeatherRemoteDataSource
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote


const val FAKE_ERROR_MESSAGE = "ERROR_MESSAGE_FAKE"

class FakeSuccessWeatherRemoteDataSourceImpl: WeatherRemoteDataSource {


    override suspend fun getWeather(query: String): Result<WeatherRemote> {
        return Result.Success(weatherRemoteSample)
    }

    override suspend fun getCities(query: String): Result<List<CityRemote>> {
        return Result.Success(citiesSample)
    }
}


class FakeErrorWeatherRemoteDataSourceImpl: WeatherRemoteDataSource {


    override suspend fun getWeather(query: String): Result<WeatherRemote> {
        return Result.Error(RequestError(code = null, message = FAKE_ERROR_MESSAGE))
    }

    override suspend fun getCities(query: String): Result<List<CityRemote>> {
        return Result.Error(RequestError(code = null, message = FAKE_ERROR_MESSAGE))
    }
}


class FakeSuccessWeatherLocalDataSourceImpl: WeatherLocalDataSource {

    override suspend fun getWeather(city: String, country: String?): Result<WeatherEntity> {
        return Result.Success(weatherLocalSample.last())
    }

    override suspend fun saveWeather(weather: WeatherEntity) {
        weatherLocalSample.add(weather)
    }

    override suspend fun getCities(city: String): Result<List<CityEntity>> {
        return Result.Success(cityLocalSample)
    }

    override suspend fun getAllCities(): Result<List<CityEntity>> {
        return Result.Success(cityLocalSample)
    }

    override suspend fun saveCities(cities: List<CityEntity>) {
        cityLocalSample.addAll(cities)

    }
}


class FakeErrorWeatherLocalDataSourceImpl: WeatherLocalDataSource {

    override suspend fun getWeather(city: String, country: String?): Result<WeatherEntity> {
        return Result.Error(RequestError(code = null, message = FAKE_ERROR_MESSAGE))
    }

    override suspend fun saveWeather(weather: WeatherEntity) {
    }

    override suspend fun getCities(city: String): Result<List<CityEntity>> {
        return Result.Error(RequestError(code = null, message = FAKE_ERROR_MESSAGE))
    }

    override suspend fun getAllCities(): Result<List<CityEntity>> {
        return Result.Error(RequestError(code = null, message = FAKE_ERROR_MESSAGE))
    }

    override suspend fun saveCities(cities: List<CityEntity>) {
    }

}