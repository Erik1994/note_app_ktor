package com.example.mynotes.data.repository.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.mynotes.R

class ConnectionManager(val context: Context) {

    fun checkNetworkConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    }

    fun getErrorMessage(context: Context, e: Exception) =
        if (checkNetworkConnection()) {
            e.message
        } else {
            context.getString(R.string.internet_connection_error)
        }
}