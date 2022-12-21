package rtp.reyhanehtpour.weatherapp.data.local

import rtp.reyhanehtpour.weatherapp.data.Result
import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity


class WeatherLocalDataSourceImpl(private val dataBase: AppDataBase) : WeatherLocalDataSource{

    override suspend fun getWeather(city: String, country:String?): Result<WeatherEntity> {
        val weathers = if (country.isNullOrEmpty()) dataBase.getWeatherDao().getWeather(city)
        else dataBase.getWeatherDao().getWeatherWithCityAndCountry(city, country)
        if (weathers.isNullOrEmpty()) return Result.EmptyResult
        return Result.Success(data = weathers!!.last())
    }

    override suspend fun saveWeather(weather: WeatherEntity) {
        dataBase.getWeatherDao().insertWeather(weather)
    }

    override suspend fun getCities(city: String): Result<List<CityEntity>> {
        val cities = dataBase.getCityDao().getCities(city)
        if (cities.isNullOrEmpty()) return Result.EmptyResult
        return Result.Success(data = cities)
    }

    override suspend fun getAllCities(): Result<List<CityEntity>> {
        return Result.Success(data = dataBase.getCityDao().getCities() ?: arrayListOf())
    }


    override suspend fun saveCities(cities: List<CityEntity>) {
        val newCities = ArrayList<CityEntity>()
        cities.forEach { city->
            val newCity = dataBase.getCityDao().getCity(city = city.cityName, country = city.country)
            if (newCity == null){
                //this is new city and should be added to the table
                newCities.add(city)
            }
        }
        dataBase.getCityDao().insertCities(newCities)
    }

}