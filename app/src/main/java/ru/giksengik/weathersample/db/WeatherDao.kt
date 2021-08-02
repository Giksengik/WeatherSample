package ru.giksengik.weathersample.db

import androidx.room.*
import io.reactivex.Single
import ru.giksengik.weathersample.models.WeatherData


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherdata")
    fun getAll() : Single<List<WeatherData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherData)

    @Delete
    fun delete(weatherData: WeatherData)

    @Update
    fun updateWeather(listOfWeatherData : List<WeatherData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weatherData: List<WeatherData>)
}