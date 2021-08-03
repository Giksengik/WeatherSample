package ru.giksengik.weathersample.ui.weatheradd

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.giksengik.weathersample.repositories.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class AddWeatherViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {


}