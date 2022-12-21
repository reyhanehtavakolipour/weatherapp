package rtp.reyhanehtpour.weatherapp.data.model



data class Weather(
    val id: String,
    val city: String,
    val country: String,
    val temp : String,
    val minTemp: String,
    val maxTemp: String,
    val description: String
)

