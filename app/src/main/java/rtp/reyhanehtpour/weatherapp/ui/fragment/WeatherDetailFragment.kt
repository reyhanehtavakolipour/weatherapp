package rtp.reyhanehtpour.weatherapp.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import rtp.reyhanehtpour.weatherapp.ARG_NAV_CITY_KEY
import rtp.reyhanehtpour.weatherapp.DEGREE_SYMBOL
import rtp.reyhanehtpour.weatherapp.R
import rtp.reyhanehtpour.weatherapp.capitalized
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.data.model.Weather
import rtp.reyhanehtpour.weatherapp.databinding.FragmentWeatherDetailBinding
import rtp.reyhanehtpour.weatherapp.ui.displayMessage
import rtp.reyhanehtpour.weatherapp.ui.getErrorMessage
import rtp.reyhanehtpour.weatherapp.ui.uiState.*
import rtp.reyhanehtpour.weatherapp.ui.viewModel.EventObserver
import rtp.reyhanehtpour.weatherapp.ui.viewModel.MainViewModel


@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {

    private var _binding : FragmentWeatherDetailBinding ?= null
    private val binding get() = _binding
    private val TAG = "WeatherDetailFragment"

    private var city : City ?= null

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCityName()
        getCityWeather()
        subscribeObservers()
        swipeRefreshListener()
    }

    private fun getCityName(){
        city = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_NAV_CITY_KEY, City::class.java)
        }else{
            arguments?.getParcelable(ARG_NAV_CITY_KEY)
        }
    }

    private fun getCityWeather(){
        city?.let { viewModel.getWeather(it) }
    }

    private fun swipeRefreshListener(){
        binding?.swipeRefresh?.setColorSchemeColors(requireContext().getColor(R.color.dark_blue));
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = false
            city?.let { viewModel.getWeather(it) }
        }
    }


    private fun subscribeObservers(){
        viewModel.weatherUiLiveData.observe(viewLifecycleOwner, EventObserver { uiState->
            when(uiState){
                is UiState.LoadingUIState ->{
                    Log.d(TAG, "WEATHER: Loading")
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.emptyLayout?.visibility = View.GONE
                }
                is UiState.SuccessUIState ->{
                    val weather = (uiState as WeatherUIState).weather
                    Log.d(TAG, "WEATHER: $weather")
                    showData(weather)
                    fillUi(isErrorState = false)
                }
                is UiState.ErrorUIState ->{
                    Log.d(TAG, "WEATHER: ${uiState.message}")
                    binding?.root?.displayMessage(getErrorMessage(requireContext(), uiState.message))
                    fillUi(isErrorState = true)
                }
                is UiState.EmptyState ->{}
            }
        })
    }

    private fun fillUi(isErrorState: Boolean){
        binding?.progressBar?.visibility = View.GONE
        if (isErrorState){
            binding?.dataLayout?.visibility = View.GONE
            binding?.emptyLayout?.visibility = View.VISIBLE
            binding?.messageTv?.text = getString(R.string.not_found)
        }else{
            binding?.dataLayout?.visibility = View.VISIBLE
            binding?.emptyLayout?.visibility = View.GONE
        }
    }

    private fun showData(weather: Weather){
        binding?.cityTv?.text = "${weather.city.capitalized()}, ${weather.country.capitalized()}"
        binding?.tempTv?.text = weather.temp + DEGREE_SYMBOL
        binding?.descriptionTv?.text = weather.description.capitalized()
        binding?.minmaxTv?.text = "L: ${weather.minTemp}$DEGREE_SYMBOL  H: ${weather.maxTemp}$DEGREE_SYMBOL"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}