package rtp.reyhanehtpour.weatherapp.data.mapper

import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.data.model.Weather
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote



fun WeatherRemote.asWeatherEntity(): WeatherEntity{
    return WeatherEntity(
        id = id,
        city = city.lowercase(),
        country = weatherSys.country.lowercase(),
        minTemp = weatherMain.minTemp,
        maxTemp = weatherMain.maxTemp,
        temp = weatherMain.temp,
        description = weatherData[0].description
    )
}


fun WeatherEntity.asWeather(): Weather{
    return Weather(
        id = id,
        city = city,
        country = country,
        minTemp = minTemp,
        maxTemp = maxTemp,
        temp = temp,
        description = description
    )
}


fun List<CityEntity>.asCities(): List<City>{
    return this.map { entity->
        City(
            id = entity.id ?: 0,
            cityName = entity.cityName,
            country = entity.country,
        )
    }
}

fun List<CityRemote>.asCitiesEntity(): List<CityEntity>{
    return map { remote->
        CityEntity(
            cityName = remote.city.lowercase(),
            country = remote.country.lowercase(),
        )
    }
}


