package rtp.reyhanehtpour.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class City(
    val id: Long,
    val cityName: String,
    val country: String
): Parcelable