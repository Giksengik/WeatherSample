package ru.giksengik.weathersample.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.giksengik.weathersample.db.LocalDataSource
import ru.giksengik.weathersample.db.RoomWeatherSource

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Reusable
    fun bindRoomWeatherSource(roomWeatherSource: RoomWeatherSource) : LocalDataSource

}