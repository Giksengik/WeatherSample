package ru.giksengik.weathersample.network

import javax.inject.Inject

class WeatherNetworkManager @Inject constructor(val api : WeatherApiJsonPlaceholder): RemoteWeatherDataProvider {

}