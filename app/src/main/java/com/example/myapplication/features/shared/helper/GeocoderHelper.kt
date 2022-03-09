package com.example.myapplication.features.shared.helper

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class GeocoderHelper(private val context: Context) {
    fun getLatitudeLongitudeFromAddress(address: String): LatLng? {
        getAddressFromString(address, 1)?.let { addresses ->
            return LatLng(
                addresses[0].latitude,
                addresses[0].longitude
            )
        } ?: return null
    }

    fun getSuggestedAddressesFromString(text: String): List<String?>? {
        getAddressFromString(text, 5)?.let { addresses ->
            val listAddress = mutableListOf<String?>()
            addresses.map {
                listAddress.add(it.getAddressLine(0))
            }
            return listAddress
        } ?: return null
    }

    fun getDistanceFromTwoLatLng(latLng1: LatLng, latLng2: LatLng): Float {
        return toLocation(latLng = latLng1).distanceTo(toLocation(latLng = latLng2))
    }

    private fun getAddressFromString(text: String, maxNumberOfResults: Int): List<Address>? {
        val coder = Geocoder(context)
        val addresses: List<Address>?
        try {
            addresses = coder.getFromLocationName(text, maxNumberOfResults)
            if (addresses == null || addresses.isEmpty()) {
                return null
            }
            return addresses
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun toLocation(latLng: LatLng): Location {
        return Location("").apply {
            latitude = latLng.latitude
            longitude = latLng.longitude
        }
    }
}
