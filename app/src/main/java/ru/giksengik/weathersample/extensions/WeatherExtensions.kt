package ru.giksengik.weathersample.extensions

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.models.CurrentWeather
import ru.giksengik.weathersample.models.WeatherData

import java.text.SimpleDateFormat
import java.util.*


fun Long.toTimeString() = SimpleDateFormat("HH:mm").format(Date(this))

fun Long.getCurrentTime(timeShift: Long) : String =
    ((this + timeShift) * 1000).toTimeString()

fun Long.getWeekDay(timeShift: Long) : String {
    val date = Date((this + timeShift) * 1000)
    val format = SimpleDateFormat("EEEE")
    return format.format(date)
}


fun WeatherData.getWeatherAtDay(day: String) : List<CurrentWeather>{
    val list = mutableListOf<CurrentWeather>()
    for(item in this.hourlyWeather){
        val tempDay = item.dt.getDay(this.timezoneOffset)
        if(tempDay == day)
            list.add(item)
        else if (list.size != 0)
            break
    }
    return list
}

fun Long.getDate(timeShift: Long) : String {
    val date = Date((this + timeShift) * 1000)
    val format = SimpleDateFormat("dd MMM")
    return format.format(date)
}

fun Long.getDay(timeShift: Long) : String{
    val date = Date((this + timeShift) * 1000)
    val format = SimpleDateFormat("dd")
    return format.format(date)
}

fun WeatherData.getTomorrow() : String{
    val today = this.currentWeather.dt.getDay(this.timezoneOffset)
    for(item in this.hourlyWeather){
        if(item.dt.getDay(this.timezoneOffset) != today)
            return item.dt.getDay(this.timezoneOffset)
    }
    return today
}



fun getTempSymbol() = "°"

fun ImageView.setWindIcon(weatherData: WeatherData, context: Context) {
        when (weatherData.currentWeather.windDeg) {
            in 21..70 -> setRotatedBitmap(R.drawable.ic_direction_right_top, context, 0f, this)
            in 71..110 -> setRotatedBitmap(R.drawable.ic_direction_right, context, 0f, this)
            in 111..160 -> setRotatedBitmap(R.drawable.ic_direction_bottom_right, context, 0f, this)
            in 161..200 -> setRotatedBitmap(R.drawable.ic_direction_right, context, 90f, this)
            in 201..250 -> setRotatedBitmap(R.drawable.ic_direction_bottom_right, context, 90f, this)
            in 251..290 -> setRotatedBitmap(R.drawable.ic_direction_right, context, 180f, this)
            in 291..340 -> setRotatedBitmap(R.drawable.ic_direction_bottom_right, context, 180f, this)
            else -> setRotatedBitmap(R.drawable.ic_direction_right, context, -90f, this)
        }
}

fun String.getWeatherImageUrl() =
 "http://openweathermap.org/img/wn/${this}@2x.png"

private fun setRotatedBitmap(iconId: Int, context: Context, angle: Float, image: ImageView){
    image.setImageDrawable(ResourcesCompat.getDrawable(context.resources, iconId, context.theme))
    image.rotation = angle
}

fun TextView.setColorWithResources(context: Context?, color: Int){
    this.setTextColor(ResourcesCompat.getColor(resources, color, context?.theme))
}

val <T> T.exhaustive: T
    get() = this

