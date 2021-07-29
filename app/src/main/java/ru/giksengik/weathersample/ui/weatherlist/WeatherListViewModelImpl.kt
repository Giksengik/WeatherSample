package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.giksengik.weathersample.repositories.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(val weatherRepository: WeatherRepository):  ViewModel() {

}