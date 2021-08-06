package ru.giksengik.weathersample.ui.weatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.giksengik.weathersample.NavHolder
import ru.giksengik.weathersample.databinding.FragmentNextWeekWeatherBinding
import ru.giksengik.weathersample.ui.NavigationButtonFragmentUser
import ru.giksengik.weathersample.ui.ToolbarHolder


class NextWeekWeatherFragment : NavigationButtonFragmentUser() {

    private var binding: FragmentNextWeekWeatherBinding? = null
    private val args by navArgs<NextWeekWeatherFragmentArgs>()
    private var dailyAdapter : DailyWeatherAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNextWeekWeatherBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationButton()
        setupList()
    }

    private fun setupList() {
        dailyAdapter = DailyWeatherAdapter(args.weatherData.timezoneOffset)
        binding?.dayWeatherList?.apply{
            layoutManager = LinearLayoutManager(context)
            this.adapter = dailyAdapter
        }
        dailyAdapter?.submitList(args.weatherData.dailyWeather.subList(0, 7))
    }


    override fun setupNavigationButton() {
        activity?.let { activity ->
            ((activity as NavHolder).getNavPlaceholder() as ToolbarHolder)
                    .configureNavigationButtonToAction(
                            NextWeekWeatherFragmentDirections
                                    .actionNextWeekWeatherFragmentToWeatherDetailsFragment(args.weatherData))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dailyAdapter = null
        binding = null
    }
}