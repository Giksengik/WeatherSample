package ru.giksengik.weathersample.network

import ru.giksengik.weathersample.network.request.Coordinates
import javax.inject.Inject

class WeatherNetworkManager @Inject constructor(private val api : WeatherApiJsonPlaceholder): RemoteWeatherDataProvider {

    override fun getWeather(coordinates: Coordinates) {
        api.getWeather(lat = coordinates.lat, lon = coordinates.lon)
            .map{

            }
    }

}
