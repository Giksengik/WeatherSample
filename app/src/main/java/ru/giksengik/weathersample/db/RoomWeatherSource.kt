package ru.giksengik.weathersample.db

import javax.inject.Inject

class RoomWeatherSource @Inject constructor(dao : WeatherDao) : LocalDataSource {

}