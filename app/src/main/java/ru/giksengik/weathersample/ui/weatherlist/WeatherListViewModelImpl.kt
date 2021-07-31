package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.giksengik.weathersample.network.request.Coordinates
import ru.giksengik.weathersample.repositories.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(val weatherRepository: WeatherRepository):  ViewModel() {

    fun getWeather(){
        weatherRepository.getWeather(Coordinates(1.55, 44.0))
    }
}