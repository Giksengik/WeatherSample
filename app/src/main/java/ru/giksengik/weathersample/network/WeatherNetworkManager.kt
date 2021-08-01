package ru.giksengik.weathersample.network

import ru.giksengik.weathersample.models.*
import ru.giksengik.weathersample.network.request.Coordinates
import ru.giksengik.weathersample.network.response.OneCallWeatherResponse
import rx.Observable
import rx.functions.Func2
import javax.inject.Inject

class WeatherNetworkManager @Inject constructor(private val api : WeatherApiJsonPlaceholder)
    : RemoteWeatherDataProvider {

    override fun getWeather(listOfCoordinates: List<Coordinates>): Observable<List<WeatherData>> {
        var list: Observable<List<OneCallWeatherResponse>> =
            api.getWeather(lat = listOfCoordinates[0].lat, lon = listOfCoordinates[0].lon)
                .map { listOf(it) }

        for (i in 1 until listOfCoordinates.size) {
            list = Observable.zip(
                list,
                api.getWeather(lat = listOfCoordinates[i].lat, lon = listOfCoordinates[i].lon)
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

    override fun getGeoQueryResults(query : String) : Observable<GeoQueryResults> =
        api.getLocationsByQuery(query)
            .map{ response ->
                GeoQueryResults(
                    locations = response.locations.map{ location ->
                        LocationData(
                            name = location.name,
                            lat = location.lat,
                            lon = location.lon,
                            country = location.country
                        )
                    }
                )
            }

}
