package rtp.reyhanehtpour.weatherapp.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import rtp.reyhanehtpour.weatherapp.*




 fun View.displayMessage(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(context.getColor(R.color.dark_orange))
        .setTextColor(context.getColor(R.color.white))
     snackBar.show()
}



fun getErrorMessage(context: Context, message: String): String{
    return when(message){
        ERROR_400 -> context.getString(R.string.bad_request_error)
        ERROR_401 -> context.getString(R.string.not_valid_authentication_error)
        ERROR_403 -> context.getString(R.string.unauthorized_request_error)
        ERROR_500 -> context.getString(R.string.service_unavailable_error)
        ERROR_404 -> context.getString(R.string.not_found_error)
        NO_INTERNET -> context.getString(R.string.no_network_error)
        TRY_AGAIN_LATER -> context.getString(R.string.try_again_error)
        else -> context.getString(R.string.try_again_error)
    }
}