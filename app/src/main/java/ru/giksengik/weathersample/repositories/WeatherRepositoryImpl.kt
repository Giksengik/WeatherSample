package ru.giksengik.weathersample.repositories

import io.reactivex.Single
import ru.giksengik.weathersample.db.LocalDataSource
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.request.LocationRequestData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataProvider: RemoteWeatherDataProvider,
    private val localDataSource: LocalDataSource)
    : WeatherRepository{

    override fun getWeather(listOfCoordinates: List<LocationRequestData>): Single<List<WeatherData>> =
            remoteWeatherDataProvider.getWeather(listOfCoordinates)
                .flatMap { data ->
                    saveAllWeather(data)
                    getWeatherDataFromLocalDataSource()
                }

    override fun saveWeather(weatherData: WeatherData) =
        localDataSource.saveWeatherData(weatherData)

    override fun saveAllWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.saveAllWeatherData(listOfWeatherData)

    override fun updateWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.updateWeatherData(listOfWeatherData)


    override fun getWeatherDataFromLocalDataSource(): Single<List<WeatherData>> =
            localDataSource.getAllWeatherData()


}