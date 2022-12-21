package rtp.reyhanehtpour.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import rtp.reyhanehtpour.weatherapp.data.local.AppDataBase
import rtp.reyhanehtpour.weatherapp.ui.WeatherApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): WeatherApplication {
        return app as WeatherApplication
    }


    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.buildDataBase(context)
    }

    @Singleton
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}