package rtp.reyhanehtpour.weatherapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import rtp.reyhanehtpour.weatherapp.ARG_NAV_CITY_KEY
import rtp.reyhanehtpour.weatherapp.ui.adapter.CityAdapter
import rtp.reyhanehtpour.weatherapp.R
import rtp.reyhanehtpour.weatherapp.data.model.City
import rtp.reyhanehtpour.weatherapp.databinding.FragmentCitiesListBinding
import rtp.reyhanehtpour.weatherapp.ui.displayMessage
import rtp.reyhanehtpour.weatherapp.ui.getErrorMessage
import rtp.reyhanehtpour.weatherapp.ui.uiState.*
import rtp.reyhanehtpour.weatherapp.ui.viewModel.EventObserver
import rtp.reyhanehtpour.weatherapp.ui.viewModel.MainViewModel


@AndroidEntryPoint
class CitiesListFragment : Fragment(), CityAdapter.CityCallback {

    private  var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding
    private val TAG = "CitiesListFragment"

    private var cityName = ""
    private val viewModel : MainViewModel by activityViewModels()
    private val adapter = CityAdapter(this)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCityName()
        initCityRv()
        getCities()
        subscribeObservers()
        swipeRefreshListener()
    }



    private fun getCities(){
        viewModel.getCities(cityName)
    }

    private fun initCityRv(){
        binding?.citiesRv?.layoutManager = LinearLayoutManager(context)
        binding?.citiesRv?.adapter = adapter
    }

    private fun getCityName(){
        cityName = arguments?.getString(ARG_NAV_CITY_KEY, "") ?: ""
    }

    private fun swipeRefreshListener(){
        binding?.swipeRefresh?.setColorSchemeColors(requireContext().getColor(R.color.dark_blue));
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = false
            viewModel.getCities(cityName)
        }
    }

    private fun subscribeObservers(){
        viewModel.citiesUiLiveData.observe(viewLifecycleOwner, EventObserver { uiState->
            when(uiState){
                is UiState.LoadingUIState ->{
                    Log.d(TAG, "CITIES: Loading")
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.emptyLayout?.visibility = View.GONE
                }
                is UiState.SuccessUIState ->{
                    val cities = (uiState as CitiesUiState).cities
                    Log.d(TAG, "CITIES: $cities")
                    showData(cities)
                    fillUi(isErrorState = false)
                }
                is UiState.ErrorUIState ->{
                    Log.d(TAG, "CITIES: ${uiState.message}")
                    binding?.root?.displayMessage(getErrorMessage(requireContext(), uiState.message))
                    fillUi(isErrorState = true)
                }
                is UiState.EmptyState ->{}
            }
        })
    }

    private fun fillUi(isErrorState: Boolean){
        binding?.progressBar?.visibility = View.GONE
        when{
            isErrorState -> {
                binding?.citiesRv?.visibility = View.GONE
                binding?.emptyLayout?.visibility = View.VISIBLE
                binding?.messageTv?.text = getString(R.string.not_found)
            }
            adapter.itemCount == 0 -> {
                binding?.citiesRv?.visibility = View.GONE
                binding?.emptyLayout?.visibility = View.VISIBLE
                binding?.messageTv?.text = getString(R.string.empty_list)
            }
            else -> {
                binding?.citiesRv?.visibility = View.VISIBLE
                binding?.emptyLayout?.visibility = View.GONE
            }
        }
    }

    private fun showData(cities: List<City>){
        adapter.addItems(cities)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun cityClickListener(city: City) {
        val bundle = Bundle()
        bundle.putParcelable(ARG_NAV_CITY_KEY, city)
        findNavController().navigate(R.id.action_weatherListFragment_to_weatherDetailFragment, bundle)
    }
}