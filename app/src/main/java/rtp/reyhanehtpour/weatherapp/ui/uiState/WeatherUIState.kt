package rtp.reyhanehtpour.weatherapp.ui.uiState

import rtp.reyhanehtpour.weatherapp.ui.viewModel.Event
import rtp.reyhanehtpour.weatherapp.data.model.Weather

data class WeatherUIState(val weather: Weather): UiState.SuccessUIState()


fun isWeatherRequestSuccessful(uiState: Event<UiState>?): Boolean{
    if (uiState?.peekContent() is UiState.SuccessUIState){
        return true
    }
    return false
}

fun isWeatherRequestFailed(uiState: Event<UiState>?): Boolean{
    if (uiState?.peekContent() is UiState.ErrorUIState){
        return true
    }
    return false
}


fun getWeather(uiState: Event<UiState>?): Weather{
    return (uiState?.peekContent() as WeatherUIState).weather
}


