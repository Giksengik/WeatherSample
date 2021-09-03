package ru.giksengik.weathersample.ui.weatherdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.HourWeatherItemBinding
import ru.giksengik.weathersample.extensions.getCurrentTime
import ru.giksengik.weathersample.extensions.getTempSymbol
import ru.giksengik.weathersample.extensions.getWeatherImageUrl
import ru.giksengik.weathersample.models.CurrentWeather
import kotlin.math.ceil

class HourlyWeatherAdapter(val timezoneOffset : Long)
    : ListAdapter<CurrentWeather, HourlyWeatherAdapter.ViewHolder>(HourWeatherDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.hour_weather_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), timezoneOffset)
    }

    class HourWeatherDiffUtil() : DiffUtil.ItemCallback<CurrentWeather>(){
        override fun areItemsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean =
                oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean =
                oldItem == newItem

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var binding : HourWeatherItemBinding? = null

        init{
            binding = HourWeatherItemBinding.bind(itemView)
        }

        @SuppressLint("SetTextI18n")
        fun bind(hourWeather: CurrentWeather, timezoneOffset: Long){
            binding?.hourOfWeather?.text =
                    hourWeather.dt.getCurrentTime(timezoneOffset)
            binding?.iconOfHourWeather?.load(hourWeather.weather[0].iconUrl.getWeatherImageUrl())
            binding?.tempOfHourWeather?.text = "${ceil(hourWeather.temp).toInt()}${getTempSymbol()}"
        }
    }

}
