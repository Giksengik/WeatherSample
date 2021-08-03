package ru.giksengik.weathersample.network

import io.reactivex.Single
import ru.giksengik.weathersample.models.*
import ru.giksengik.weathersample.network.request.LocationRequestData
import ru.giksengik.weathersample.network.response.OneCallWeatherResponse
import javax.inject.Inject

class WeatherNetworkManager @Inject constructor(private val api : WeatherApiJsonPlaceholder)
    : RemoteWeatherDataProvider {

    override fun getWeather(listOfCoordinates: List<LocationRequestData>): Single<List<WeatherData>> {
        var list: Single<List<OneCallWeatherResponse>> =
            api.getWeather(lat = listOfCoordinates[0].lat, lon = listOfCoordinates[0].lon)
                .map {
                    val response = it
                    response.name =  listOfCoordinates[0].name
                    response.region =  listOfCoordinates[0].region
                    listOf(response)
                }

        for (i in 1 until listOfCoordinates.size) {
            list = Single.zip(
                list,
                api.getWeather(lat = listOfCoordinates[i].lat, lon = listOfCoordinates[i].lon)
                    .map{
                        val response = it
                        response.name =  listOfCoordinates[i].name
                        response.region =  listOfCoordinates[i].region
                        listOf(response)
                    }
            ) { t1, t2 ->
                t1 + t2
            }
        }

        return list
            .map { responseList ->
                return@map responseList.map { response ->
                    WeatherData(
                        lat = response.lat,
                        lon = response.lon,
                        region = response.region,
                        name = response.name,
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
                        hourlyWeather = response.hourly.map { hourly ->
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
                        dailyWeather = response.daily.map { daily ->
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
                                weather = daily.weather.map { weather ->
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
                        weatherAlerts = response.alerts.map { alert ->
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

    override fun getGeoQueryResults(query : String) : Single<List<LocationData>> =
        api.getLocationsByQuery(query)
            .map{ locations ->
                    locations.map{ location ->
                        LocationData(
                            name = location.name,
                            lat = location.lat,
                            lon = location.lon,
                            country = location.country
                        )
                    }
            }

}
