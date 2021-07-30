package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor( val remoteWeatherDataProvider: RemoteWeatherDataProvider) : WeatherRepository{


}