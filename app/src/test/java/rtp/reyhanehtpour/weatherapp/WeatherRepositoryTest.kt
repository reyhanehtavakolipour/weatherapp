package rtp.reyhanehtpour.weatherapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import rtp.reyhanehtpour.weatherapp.data.data
import rtp.reyhanehtpour.weatherapp.data.errorMessage
import rtp.reyhanehtpour.weatherapp.data.mapper.asWeatherEntity
import rtp.reyhanehtpour.weatherapp.data.repository.WeatherRepository
import rtp.reyhanehtpour.weatherapp.sharedTest.*


class WeatherRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val weatherRepositorySuccessResult = WeatherRepository(
        weatherRemoteDataSource = FakeSuccessWeatherRemoteDataSourceImpl(),
        weatherLocalDataSourceImpl = FakeSuccessWeatherLocalDataSourceImpl()
    )
    private val weatherRepositoryErrorResult = WeatherRepository(
        weatherRemoteDataSource = FakeErrorWeatherRemoteDataSourceImpl(),
        weatherLocalDataSourceImpl = FakeErrorWeatherLocalDataSourceImpl()
    )


    @Test
    fun get_weather_test() = runBlocking {
        val actualWeather = weatherRepositorySuccessResult.getRemoteWeather("london").data
        Assert.assertEquals(weatherRemoteSample, actualWeather)
    }

    @Test
    fun get_cities_test() = runBlocking {
        val actualWeather = weatherRepositorySuccessResult.getRemoteCities("london").data
        Assert.assertEquals(citiesSample, actualWeather)
    }

    @Test
    fun get_weather_remote_city_test() = runBlocking {
        val actualCity = weatherRepositorySuccessResult.getRemoteWeather("london").data.city
        Assert.assertEquals(weatherRemoteSample.city, actualCity)
    }

    @Test
    fun get_weather_local_db_temp_test() = runBlocking {
        weatherLocalSample.add(weatherRemoteSample.asWeatherEntity())
        val actualWeatherState = weatherRepositorySuccessResult.getLocalWeather("london", "CA").data.temp
        Assert.assertEquals(weatherRemoteSample.weatherMain.temp, actualWeatherState)
    }

    @Test
    fun get_error_weather_test() = runBlocking {
        val actualMessage = weatherRepositoryErrorResult.getRemoteWeather("london").errorMessage
        Assert.assertEquals(actualMessage, FAKE_ERROR_MESSAGE)
    }

}