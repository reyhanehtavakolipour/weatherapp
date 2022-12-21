package rtp.reyhanehtpour.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rtp.reyhanehtpour.weatherapp.data.local.dao.CityDao
import rtp.reyhanehtpour.weatherapp.data.local.dao.WeatherDao
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity


@Database(entities = [WeatherEntity::class, CityEntity::class], version = 1)
//@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getWeatherDao(): WeatherDao
    abstract fun getCityDao(): CityDao


    companion object{

        private const val dbName = "weather_db"

        fun buildDataBase(context: Context): AppDataBase{
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                dbName
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }


}