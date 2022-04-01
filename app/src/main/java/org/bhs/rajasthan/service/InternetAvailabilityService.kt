package org.bhs.rajasthan.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log


object InternetAvailabilityService {
    fun isInternetAccessible(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

        var internetStatus = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val currentNetwork = connectivityManager.getActiveNetwork()
            val caps = connectivityManager.getNetworkCapabilities(currentNetwork)

            Log.i("InternetAvailabilityService", currentNetwork.toString())
            Log.i("InternetAvailabilityService", caps.toString())
            Log.i(
                "InternetAvailabilityService",
                caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED).toString()
            )
            internetStatus =
                caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) ?: false
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            internetStatus = activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        return internetStatus
    }
}