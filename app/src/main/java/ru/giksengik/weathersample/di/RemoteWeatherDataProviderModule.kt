package ru.giksengik.weathersample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.WeatherNetworkManager


@Module
@InstallIn(SingletonComponent::class)
interface RemoteWeatherDataProviderModule {
    @Binds
    fun bindRemoteWeatherDataProvider(weatherNetworkManager: WeatherNetworkManager) : RemoteWeatherDataProvider
}