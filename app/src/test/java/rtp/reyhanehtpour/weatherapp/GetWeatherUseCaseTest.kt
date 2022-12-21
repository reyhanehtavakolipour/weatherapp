package rtp.reyhanehtpour.weatherapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.data
import rtp.reyhanehtpour.weatherapp.data.errorMessage
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.data.repository.WeatherRepository
import rtp.reyhanehtpour.weatherapp.domain.usecases.GetCitiesUseCase
import rtp.reyhanehtpour.weatherapp.domain.usecases.GetWeatherUseCase
import rtp.reyhanehtpour.weatherapp.domain.usecases.RequestType
import rtp.reyhanehtpour.weatherapp.domain.usecases.UseCase
import rtp.reyhanehtpour.weatherapp.sharedTest.*

class GetWeatherUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    val city = City(id = 12, cityName = "london", country = "CA")


    class ExceptionUseCase(dispatcher: CoroutineDispatcher) : UseCase<Unit, Unit, RequestType>(dispatcher) {
        override suspend fun remoteExecute(parameters: Unit): Result<Unit> {
            throw Exception("remote test exception")
        }

        override suspend fun localExecute(parameters: Unit): Result<Unit> {
            throw Exception("local test exception")
        }
    }


    private val weatherRepositorySuccessResult = WeatherRepository(
        weatherRemoteDataSource = FakeSuccessWeatherRemoteDataSourceImpl(),
        weatherLocalDataSourceImpl = FakeSuccessWeatherLocalDataSourceImpl()
    )
    private val weatherRepositoryErrorResult = WeatherRepository(
        weatherRemoteDataSource = FakeErrorWeatherRemoteDataSourceImpl(),
        weatherLocalDataSourceImpl = FakeErrorWeatherLocalDataSourceImpl()
    )


    private val weatherUseCase = GetWeatherUseCase(
        weatherRepository = weatherRepositorySuccessResult,
        ioDispatcher = coroutineRule.testDispatcher
    )

    private val citiesUseCase = GetCitiesUseCase(
        weatherRepository = weatherRepositorySuccessResult,
        ioDispatcher = coroutineRule.testDispatcher
    )

    private val weatherUseCaseError = GetWeatherUseCase(
        weatherRepository = weatherRepositoryErrorResult,
        ioDispatcher = coroutineRule.testDispatcher
    )


    @Test
    fun get_cities_size_test()= runBlocking {
        val actualValue = citiesUseCase.remoteExecute("london").data.size
        Assert.assertEquals(citiesSample.size, actualValue)
    }

    @Test
    fun get_weather_city_test()= runBlocking {
        val actualValue = weatherUseCase.remoteExecute(city).data.city
        Assert.assertEquals(weatherRemoteSample.city, actualValue)
    }


    @Test
    fun get_weather_country_test() = runBlocking {
        val actualCountry = weatherUseCase.remoteExecute(city).data.country
        Assert.assertEquals(weatherRemoteSample.weatherSys.country.lowercase(), actualCountry.lowercase())
    }

    @Test
    fun get_weather_error_test() = runBlocking {
        val actualError = weatherUseCaseError.remoteExecute(city).errorMessage
        Assert.assertEquals(TRY_AGAIN_LATER, actualError)
    }

    @Test
    fun exception_error_test() = runBlocking {
        val useCase = ExceptionUseCase(coroutineRule.testDispatcher)
        val result = useCase(Unit, RequestType.REMOTE_REQUEST)
        Assert.assertTrue(result is Result.Error)
    }

}