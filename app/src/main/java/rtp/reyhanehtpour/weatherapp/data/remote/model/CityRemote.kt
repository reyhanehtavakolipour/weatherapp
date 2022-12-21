package rtp.reyhanehtpour.weatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class CityRemote(
    @SerializedName("name")
    val city: String,
    @SerializedName("country")
    val country: String
)