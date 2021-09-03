package ru.giksengik.weathersample.ui.weatherlist

import ru.giksengik.weathersample.models.WeatherData

sealed class WeatherListViewState {

    class Success {
        data class Loaded(val weatherList : List<WeatherData>) : WeatherListViewState()

        object WeatherUpdated : WeatherListViewState()

        object WeatherDeleted : WeatherListViewState()
    }

    object Loading : WeatherListViewState()

    class Error {

        object NetworkError : WeatherListViewState()

        object HttpError : WeatherListViewState()

    }

}




