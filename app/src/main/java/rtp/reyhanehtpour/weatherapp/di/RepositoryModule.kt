package rtp.reyhanehtpour.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rtp.reyhanehtpour.weatherapp.data.local.WeatherLocalDataSource
import rtp.reyhanehtpour.weatherapp.data.remote.WeatherRemoteDataSource
import rtp.reyhanehtpour.weatherapp.data.repository.WeatherRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherRemoteDataSource: WeatherRemoteDataSource,
        weatherLocalDataSource: WeatherLocalDataSource
    ): WeatherRepository {
        return WeatherRepository(
            weatherRemoteDataSource = weatherRemoteDataSource,
            weatherLocalDataSourceImpl = weatherLocalDataSource
        )
    }

}