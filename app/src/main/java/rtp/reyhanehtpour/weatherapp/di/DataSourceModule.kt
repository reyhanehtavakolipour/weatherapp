package rtp.reyhanehtpour.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rtp.reyhanehtpour.weatherapp.data.local.AppDataBase
import rtp.reyhanehtpour.weatherapp.data.local.WeatherLocalDataSource
import rtp.reyhanehtpour.weatherapp.data.local.WeatherLocalDataSourceImpl
import rtp.reyhanehtpour.weatherapp.data.remote.WeatherRemoteDataSource
import rtp.reyhanehtpour.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import rtp.reyhanehtpour.weatherapp.data.remote.service.WeatherService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideWeatherDataSource(
        weatherService: WeatherService,
    ): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(
            weatherService = weatherService
        )
    }


    @Singleton
    @Provides
    fun provideWeatherLocalDataSource(appDataBase: AppDataBase): WeatherLocalDataSource {
        return WeatherLocalDataSourceImpl(appDataBase)
    }
}