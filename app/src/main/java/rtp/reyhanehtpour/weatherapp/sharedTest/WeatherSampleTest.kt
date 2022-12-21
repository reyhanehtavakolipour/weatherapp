package rtp.reyhanehtpour.weatherapp.sharedTest

import rtp.reyhanehtpour.weatherapp.data.local.entity.CityEntity
import rtp.reyhanehtpour.weatherapp.data.local.entity.WeatherEntity
import rtp.reyhanehtpour.weatherapp.data.remote.model.*


val weatherRemoteSample = WeatherRemote(
    id = "3458fhd34",
    city = "london",
    weatherData =  arrayListOf(
        WeatherDataRemote(
            main = "",
            description = ""
        )
    ),
    weatherSys = WeatherSys(
        country = "CA"
    ),
    weatherMain = WeatherMainRemote(
        temp = "230",
        minTemp = "",
        maxTemp = ""
    )
)


val citiesSample = arrayListOf(
    CityRemote(
        city = "london",
        country = "GB"
    ),
    CityRemote(
        city = "lon",
        country = "NB"
    ),
    CityRemote(
        city = "londion",
        country = "IK"
    ),
    CityRemote(
        city = "london",
        country = "CA"
    ),
    CityRemote(
        city = "london",
        country = "GBC"
    ),
)




var weatherLocalSample = ArrayList<WeatherEntity>()

var cityLocalSample = ArrayList<CityEntity>()





