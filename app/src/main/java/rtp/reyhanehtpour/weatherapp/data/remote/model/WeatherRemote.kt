package rtp.reyhanehtpour.weatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherRemote(
    @SerializedName("main")
    val weatherMain: WeatherMainRemote,
    @SerializedName("weather")
    val weatherData: List<WeatherDataRemote>,
    @SerializedName("name")
    val city: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("sys")
    val weatherSys: WeatherSys
)


data class WeatherSys(
    @SerializedName("country")
    val country: String
)

data class WeatherMainRemote(
    @SerializedName("temp")
    val temp: String,
    @SerializedName("temp_min")
    val minTemp : String,
    @SerializedName("temp_max")
    val maxTemp : String
)

data class WeatherDataRemote(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description : String,
)