package ru.giksengik.weathersample.di
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.giksengik.weathersample.db.WeatherDao
import ru.giksengik.weathersample.db.WeatherDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Reusable
    fun provideWeatherDatabase(@ApplicationContext context : Context) : WeatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, "weather-db")
                    .build()
    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase) : WeatherDao = weatherDatabase.weatherDao()

}