package rtp.reyhanehtpour.weatherapp.ui.uiState

import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.ui.viewModel.Event

data class CitiesUiState(
   val cities: List<City>
) : UiState.SuccessUIState()

fun isCitiesRequestSuccessful(uiState: Event<UiState>?): Boolean{
    if (uiState?.peekContent() is UiState.SuccessUIState){
        return true
    }
    return false
}

fun isCitiesRequestFailed(uiState: Event<UiState>?): Boolean{
    if (uiState?.peekContent() is UiState.ErrorUIState){
        return true
    }
    return false
}


fun getCities(uiState: Event<UiState>?): List<City> {
    return (uiState?.peekContent() as CitiesUiState).cities
}
