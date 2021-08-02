package ru.giksengik.weathersample.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.giksengik.weathersample.models.WeatherData
import rx.Observable


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherdata")
    fun getAll() : Observable<List<WeatherData>>

    @Insert
    fun insert(weatherData: WeatherData)

    @Delete
    fun delete(weatherData: WeatherData)
}