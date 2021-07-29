package ru.giksengik.weathersample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.giksengik.weathersample.repositories.WeatherRepository
import ru.giksengik.weathersample.repositories.WeatherRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface WeatherRepositoryModule {

    @Binds
    fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository

}