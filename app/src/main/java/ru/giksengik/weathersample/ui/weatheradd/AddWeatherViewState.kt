package ru.giksengik.weathersample.ui.weatheradd

import ru.giksengik.weathersample.models.GeoQueryResults
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData

sealed class AddWeatherViewState{

    class Error {

        object HttpError : AddWeatherViewState()

        object NetworkError : AddWeatherViewState()

        object PlaceAlreadyWritten : AddWeatherViewState()

    }

    data class LocationsLoaded(val results : List<LocationData>) : AddWeatherViewState()

    object WeatherPlaceAdded : AddWeatherViewState()

    object Loading : AddWeatherViewState()
}
