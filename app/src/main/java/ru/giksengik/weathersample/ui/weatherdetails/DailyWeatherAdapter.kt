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
import ru.giksengik.weathersample.databinding.DayWeatherItemBinding
import ru.giksengik.weathersample.extensions.getTempSymbol
import ru.giksengik.weathersample.extensions.getWeatherImageUrl
import ru.giksengik.weathersample.extensions.getWeekDay
import ru.giksengik.weathersample.models.DailyWeather
import kotlin.math.ceil

class DailyWeatherAdapter (private val timezoneShift: Long) :
    ListAdapter<DailyWeather, DailyWeatherAdapter.ViewHolder>(DailyWeatherDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.day_weather_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dailyWeather = getItem(position), timezoneShift = timezoneShift )

    class DailyWeatherDiffUtil : DiffUtil.ItemCallback<DailyWeather>(){
        override fun areItemsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean =
                oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean =
                oldItem == newItem

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var binding : DayWeatherItemBinding? = null

        init{
            binding = DayWeatherItemBinding.bind(itemView)
        }

        @SuppressLint("SetTextI18n")
        fun bind(dailyWeather: DailyWeather, timezoneShift : Long){
            binding?.dayOfWeekWeatherIcon?.load(dailyWeather.weather[0].iconUrl.getWeatherImageUrl())
            binding?.tempOfDay?.text = "${ceil(dailyWeather.temp.day).toInt()}${getTempSymbol()}"
            binding?.dayOfWeek?.text = dailyWeather.dt.getWeekDay(timezoneShift)
        }
    }

}
