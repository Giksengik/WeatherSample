package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.db.LocalDataSource
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.request.LocationRequestData
import rx.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataProvider: RemoteWeatherDataProvider,
    private val localDataSource: LocalDataSource)
    : WeatherRepository{

    override fun getWeather(listOfCoordinates: List<LocationRequestData>): Observable<List<WeatherData>> =
            remoteWeatherDataProvider.getWeather(listOfCoordinates)
                .flatMap { data ->
                    updateWeather(data)
                    getWeatherDataFromLocalDataSource()
                }



    override fun saveWeather(weatherData: WeatherData) =
        localDataSource.saveWeatherData(weatherData)


    override fun updateWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.updateWeatherData(listOfWeatherData)


    override fun getWeatherDataFromLocalDataSource(): Observable<List<WeatherData>> =
            localDataSource.getAllWeatherData()


}