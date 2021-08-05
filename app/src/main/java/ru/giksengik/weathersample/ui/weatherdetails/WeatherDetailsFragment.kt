package ru.giksengik.weathersample.ui.weatherdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.clear
import coil.load
import ru.giksengik.weathersample.NavHolder
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.FragmentWeatherDetailsBinding
import ru.giksengik.weathersample.extensions.*
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.ui.NavigationButtonFragmentUser
import ru.giksengik.weathersample.ui.ToolbarHolder
import java.nio.file.WatchEvent

class WeatherDetailsFragment : NavigationButtonFragmentUser() {

    private var binding: FragmentWeatherDetailsBinding? = null
    private val args by navArgs<WeatherDetailsFragmentArgs>()
    private var adapter: HourlyWeatherAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherDetailsBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationButton()
        val weatherData = args.weatherData
        setupStandart(weatherData)
        setupToday(weatherData)

        binding?.textTomorrow?.setOnClickListener{
            setupTomorrow(weatherData)
        }

        binding?.textToday?.setOnClickListener{
            setupToday(weatherData)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupStandart(weatherData: WeatherData) {
        binding?.placeName?.text = "${weatherData.name},"
        binding?.placeRegion?.text = weatherData.region
        binding?.weatherChangeList?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = HourlyWeatherAdapter(weatherData.timezoneOffset)
        binding?.weatherChangeList?.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    fun setupToday(weatherData: WeatherData) {
        binding?.textToday?.setColorWithResources(context, R.color.white)
        binding?.textTomorrow?.setColorWithResources(context, R.color.light_grey)
        binding?.textNext7Days?.setColorWithResources(context, R.color.light_grey)
        binding?.weatherDay?.text = getString(R.string.today)
        if (weatherData.currentWeather.weather.isNotEmpty()) {
            binding?.weatherDescription?.text = weatherData.currentWeather.weather[0].main
            binding?.dayWeatherIcon?.load(weatherData.currentWeather.weather[0].iconUrl.getWeatherImageUrl())
        } else {
            binding?.weatherDescription?.text = ""
            binding?.dayWeatherIcon?.clear()
        }
        binding?.dayWeatherTemp?.text = "${kotlin.math.ceil(weatherData.currentWeather.temp).toInt()}${getTempSymbol()}"
        binding?.weatherDate?.text = weatherData.currentWeather.dt.getDate(weatherData.timezoneOffset)
        adapter?.submitList(weatherData.
        getWeatherAtDay(weatherData.currentWeather.dt.getDay(weatherData.timezoneOffset)))
    }

    @SuppressLint("SetTextI18n")
    fun setupTomorrow(weatherData: WeatherData){
        binding?.textToday?.setColorWithResources(context, R.color.light_grey)
        binding?.textTomorrow?.setColorWithResources(context, R.color.white)
        binding?.textNext7Days?.setColorWithResources(context, R.color.light_grey)
        binding?.weatherDay?.text = getString(R.string.tomorrow)
        binding?.weatherDescription?.text = weatherData.dailyWeather[1].weather[0].main
        binding?.dayWeatherIcon?.load(weatherData.dailyWeather[1].weather[0].iconUrl.getWeatherImageUrl())
        binding?.dayWeatherTemp?.text = "${kotlin.math.ceil(weatherData.dailyWeather[1].temp.day).toInt()}${getTempSymbol()}"
        binding?.weatherDate?.text = weatherData.currentWeather.dt.getDate(weatherData.dailyWeather[1].dt)
        adapter?.submitList(weatherData.
        getWeatherAtDay(weatherData.getTomorrow()))
    }

    override fun setupNavigationButton() {
        activity?.let { activity ->
            ((activity as NavHolder).getNavPlaceholder() as ToolbarHolder)
                    .configureNavigationButtonToAction(WeatherDetailsFragmentDirections.actionWeatherDetailsFragmentToWeatherList())
        }
        findNavController()

    }

    override fun onDetach() {
        super.onDetach()
        binding = null
        adapter = null
    }
}