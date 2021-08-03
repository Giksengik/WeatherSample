package ru.giksengik.weathersample.db

import androidx.room.*
import io.reactivex.Single
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData


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

    @Query("SELECT EXISTS(SELECT * FROM weatherdata WHERE lat = :lat AND lon = :lon)")
    fun hasThisWeather(lat : Double, lon : Double) : Boolean

}