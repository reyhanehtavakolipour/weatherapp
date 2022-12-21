package rtp.reyhanehtpour.weatherapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rtp.reyhanehtpour.weatherapp.data.*
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.domain.usecases.GetCitiesUseCase
import rtp.reyhanehtpour.weatherapp.domain.usecases.GetWeatherUseCase
import rtp.reyhanehtpour.weatherapp.domain.usecases.RequestType
import rtp.reyhanehtpour.weatherapp.ui.uiState.CitiesUiState
import rtp.reyhanehtpour.weatherapp.ui.uiState.WeatherUIState
import rtp.reyhanehtpour.weatherapp.ui.uiState.UiState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val getWeatherUseCase: GetWeatherUseCase,
    val getCitiesUseCase: GetCitiesUseCase
): ViewModel() {


    private val _weatherUiLiveData = MutableLiveData<Event<UiState>>()
    val weatherUiLiveData : LiveData<Event<UiState>> get() = _weatherUiLiveData

    private val _citiesUiLiveData = MutableLiveData<Event<UiState>>()
    val citiesUiLiveData : LiveData<Event<UiState>> get() = _citiesUiLiveData

    /**
     * Getting data using a single source of truth which is room db.
     * Step1: Get weather information from local db to show sth to user as fast as possible
     * Step2: Call api to get fresh data
     * Step4: Save the api response to local db
     * Step5: Get data from local db
     */
    fun getWeather(city: City){
        viewModelScope.launch {

            //show loading state
            _weatherUiLiveData.value = Event(UiState.LoadingUIState)

            //first get data from local db
            getWeatherUseCase(city, RequestType.LOCAL_REQUEST).let { result ->
                when{
                    result.succeeded ->  _weatherUiLiveData.value = Event(WeatherUIState(result.data))
                    // the first time that user opens the app the local db is empty and in this case we don't want to show any error to him/her
                    result.isEmpty -> _weatherUiLiveData.value = Event(UiState.EmptyState)
                    result.failed -> _weatherUiLiveData.value = Event(UiState.ErrorUIState(result.errorMessage))
                }
           }

            //then call api to get data and then save it to local db
            getWeatherUseCase(city, RequestType.REMOTE_REQUEST).let { result ->
            if (result.succeeded){
                _weatherUiLiveData.value = Event(WeatherUIState(result.data))
            }else{
                _weatherUiLiveData.value = Event(UiState.ErrorUIState(result.errorMessage))
            }
         }
      }
    }


    fun getCities(query: String){
        viewModelScope.launch {

            //show loading state
            _citiesUiLiveData.value = Event(UiState.LoadingUIState)

            //first get data from local db
            getCitiesUseCase(query, RequestType.LOCAL_REQUEST).let { result ->
                when{
                    result.succeeded -> _citiesUiLiveData.value = Event(CitiesUiState(result.data))
                    // the first time that user opens the app the local db is empty and in this case we don't want to show any error to him/her
                    result.isEmpty -> _citiesUiLiveData.value = Event(UiState.EmptyState)
                    result.failed -> _citiesUiLiveData.value = Event(UiState.ErrorUIState(result.errorMessage))
                }
            }

            //then call api to get data and then save it to local db
            getCitiesUseCase(query, RequestType.REMOTE_REQUEST).let { result ->
                if (result.succeeded){
                    _citiesUiLiveData.value = Event(CitiesUiState(result.data))
                }
                else if (result.isEmpty){
                    _citiesUiLiveData.value = Event(CitiesUiState(arrayListOf()))
                }
                else{
                    _citiesUiLiveData.value = Event(UiState.ErrorUIState(result.errorMessage))
                }
            }
        }

    }

}