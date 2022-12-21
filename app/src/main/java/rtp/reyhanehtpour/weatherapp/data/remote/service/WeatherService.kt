package rtp.reyhanehtpour.weatherapp.data.remote.service

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Url
import rtp.reyhanehtpour.weatherapp.data.remote.model.CityRemote
import rtp.reyhanehtpour.weatherapp.data.remote.model.WeatherRemote

interface WeatherService {

    @GET
    suspend fun getWeather(@Url url: String) : Response<WeatherRemote>

    @GET
    suspend fun getCities(@Url url: String): Response<List<CityRemote>>

}