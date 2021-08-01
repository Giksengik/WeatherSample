package ru.giksengik.weathersample.ui.weatherlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.WeatherItemInListBinding
import ru.giksengik.weathersample.extensions.getCurrentTime
import ru.giksengik.weathersample.extensions.getWeatherImageUrl
import ru.giksengik.weathersample.extensions.setWindIcon
import ru.giksengik.weathersample.models.WeatherData
import kotlin.math.ceil


class WeatherListAdapter : ListAdapter<WeatherData, WeatherListAdapter.ViewHolder>(
    WeatherDataDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.weather_item_in_list,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(getItem(position))
    }


    class WeatherDataDiffUtil : DiffUtil.ItemCallback<WeatherData>(){
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
            oldItem.lat == newItem.lat && oldItem.lon == newItem.lon


        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
            oldItem == newItem

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var binding : WeatherItemInListBinding? = null

        init{
            binding = WeatherItemInListBinding.bind(itemView)
        }

        fun bind(weatherData: WeatherData){
            binding?.region?.text = weatherData.name
            binding?.district?.text = weatherData.region
            binding?.temperature?.text = ceil(weatherData.currentWeather.temp).toInt().toString()
            binding?.time?.text = weatherData.currentWeather.dt.getCurrentTime(weatherData.timezoneOffset)
            binding?.root?.context?.let { context ->
                binding?.windDirection?.setWindIcon(weatherData = weatherData, context = context)
            }
            val url = weatherData.currentWeather.weather[0].iconUrl.getWeatherImageUrl()
            binding?.weatherIcon?.load(url)

        }


    }

}