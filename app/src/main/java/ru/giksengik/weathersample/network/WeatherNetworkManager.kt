package ru.giksengik.weathersample.network

import kotlinx.coroutines.currentCoroutineContext
import ru.giksengik.weathersample.models.*
import ru.giksengik.weathersample.network.request.Coordinates
import javax.inject.Inject

class WeatherNetworkManager @Inject constructor(private val api : WeatherApiJsonPlaceholder): RemoteWeatherDataProvider {

    override fun getWeather(coordinates: Coordinates) {
        api.getWeather(lat = coordinates.lat, lon = coordinates.lon)
            .map{ response ->
                WeatherData(
                    lat = response.lat,
                    lon = response.lon,
                    timezone = response.timezone,
                    timezoneOffset = response.timezoneOffset,
                    currentWeather = CurrentWeather(
                        dt = response.current.dt,
                        sunrise = response.current.sunrise,
                        sunset = response.current.sunset,
                        feelsLike = response.current.feelsLike,
                        temp = response.current.temp,
                        clouds = response.current.clouds,
                        windDeg = response.current.windDeg,
                        weather = response.current.weather.map { weather ->
                            Weather(
                                id = weather.id,
                                main = weather.main,
                                description = weather.description,
                                iconUrl = weather.icon
                            )
                        },
                        rain = Rain(
                            the1H = response.current.rain.the1H
                        )
                    ),
                    hourlyWeather = response.hourly.map{ hourly ->
                        CurrentWeather(
                            dt = hourly.dt,
                            sunrise = hourly.sunrise,
                            sunset = hourly.sunset,
                            feelsLike = hourly.feelsLike,
                            temp = hourly.temp,
                            clouds = hourly.clouds,
                            windDeg = hourly.windDeg,
                            weather = hourly.weather.map { weather ->
                                Weather(
                                    id = weather.id,
                                    main = weather.main,
                                    description = weather.description,
                                    iconUrl = weather.icon
                                )
                            },
                            rain = Rain(
                                the1H = hourly.rain.the1H
                            )
                        )
                    },
                    dailyWeather = response.daily.map{ daily ->
                        DailyWeather(
                            dt = daily.dt,
                            sunrise = daily.sunrise,
                            sunset = daily.sunset,
                            moonPhase = daily.moonPhase,
                            moonrise = daily.moonrise,
                            moonset = daily.moonset,
                            temp = Temperature(
                                day = daily.temp.day,
                                min = daily.temp.min,
                                max = daily.temp.max,
                                night = daily.temp.night,
                                eve = daily.temp.eve,
                                morn = daily.temp.morn
                            ),
                            feelsLike = FeelsLike(
                                day = daily.feelsLike.day,
                                night = daily.feelsLike.night,
                                eve = daily.feelsLike.eve,
                                morn = daily.feelsLike.morn
                            ),
                            windDeg = daily.windDeg,
                            weather = daily.weather.map{ weather ->
                                Weather(
                                    id = weather.id,
                                    main = weather.main,
                                    description = weather.description,
                                    iconUrl = weather.icon
                                )
                            },
                            clouds = daily.clouds,
                            rain = daily.rain
                        )
                    },
                    weatherAlerts = response.alerts.map{ alert ->
                        WeatherAlert(
                            senderName = alert.senderName,
                            event = alert.event,
                            start = alert.start,
                            end = alert.end,
                            description = alert.description,
                            tags = alert.tags
                        )
                    }
                )
            }
    }

}
