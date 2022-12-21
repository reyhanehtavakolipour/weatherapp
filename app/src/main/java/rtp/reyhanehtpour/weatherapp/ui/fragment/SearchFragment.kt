package rtp.reyhanehtpour.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rtp.reyhanehtpour.weatherapp.ARG_NAV_CITY_KEY
import rtp.reyhanehtpour.weatherapp.R


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var cityName = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply{
            setContent {
                BuildView()
            }
        }
    }

    @Composable
    fun SearchCityTextField() {
        var city by rememberSaveable { mutableStateOf("") }

        TextField(
            singleLine = true,
            value = city,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp, horizontal = 36.dp),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(Icons.Filled.Search, "")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor =  colorResource(R.color.blue),
                cursorColor = colorResource(R.color.blue),
                focusedLabelColor = colorResource(R.color.blue),
            ),
            onValueChange = {
                city = it
            },
            label = { Text(stringResource(id = R.string.search_city)) },
        )

        cityName = city
    }

    @Composable
    fun ShowWeatherButton(){
        Button(
            onClick = {
                val bundle = Bundle()
                bundle.putString(ARG_NAV_CITY_KEY, cityName.trimEnd().trimStart())
                findNavController().navigate(R.id.action_searchFragment_to_weatherListFragment, bundle)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.blue))
        )
        {
            Text(text = stringResource(id = R.string.show_weather), color = Color.White)
        }
    }

    @Composable
    fun BuildView() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchCityTextField()
            ShowWeatherButton()
        }
    }

}

