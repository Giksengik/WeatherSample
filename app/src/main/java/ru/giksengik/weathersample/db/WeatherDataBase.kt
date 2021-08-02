package ru.giksengik.weathersample.db

import androidx.room.Database
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.giksengik.weathersample.models.WeatherData


@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDataBase {
    abstract fun weatherDataDao() : WeatherDataDao
}