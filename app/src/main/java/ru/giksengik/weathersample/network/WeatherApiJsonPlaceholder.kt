package ru.giksengik.weathersample.network


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.network.response.GeoQueryResponse
import ru.giksengik.weathersample.network.response.LocationResponse
import ru.giksengik.weathersample.network.response.OneCallWeatherResponse


interface WeatherApiJsonPlaceholder {

    companion object{
        private const val ALL_EXCLUDE = """
            current
            """
        private const val METRIC_UNITS = "metric"
        private const val ENGLISH_LANG = "en"
        private const val STANDART_LIMIT = 5
    }

    @GET("data/2.5/onecall")
    fun getWeather(@Query("lat") lat : Double, @Query("lon") lon : Double,
                   @Query("exclude") exclude : String = ALL_EXCLUDE,
                   @Query("units") units : String = METRIC_UNITS,
                   @Query("lang") lang : String = ENGLISH_LANG
    ) : Single<OneCallWeatherResponse>

    @GET("/geo/1.0/direct?")
    fun getLocationsByQuery(@Query("q") query : String,
                             @Query("limit") limit : Int = STANDART_LIMIT)
    : Single<List<LocationResponse>>

}