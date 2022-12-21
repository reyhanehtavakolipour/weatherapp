package rtp.reyhanehtpour.weatherapp


const val BASE_URL = "https://api.openweathermap.org"
const val WEATHER_APP_ID = "4e639eaa2cd849247e00e0445143370f"
const val CITY_LIMIT = 20
const val NO_INTERNET = "Please Connect to the Internet!"
const val ERROR_400 = "Bad Request!"
const val ERROR_500 = "Service Unavailable!"
const val ERROR_401 = "Authentication not Valid!"
const val ERROR_403 = "Unauthorized Request!"
const val ERROR_404 = "Page Not Found!"
const val UNABLE_RESOLVE_HOST= "Unable to resolve host"
const val TRY_AGAIN_LATER = "Try again later"
const val ARG_NAV_CITY_KEY = "arg_nav_city_key"
const val DEGREE_SYMBOL = "\u00B0"

fun getCitiesUrl(city: String): String{
    return "https://api.openweathermap.org/geo/1.0/direct?q=$city&limit=$CITY_LIMIT&appid=$WEATHER_APP_ID"
}

fun getWeatherUrl(city: String): String{
    return "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$WEATHER_APP_ID"
}
