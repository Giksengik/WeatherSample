package ru.giksengik.weathersample.ui.weatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.giksengik.weathersample.NavHolder
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.FragmentWeatherDetailsBinding
import ru.giksengik.weathersample.ui.NavigationButtonFragmentUser
import ru.giksengik.weathersample.ui.ToolbarHolder

class WeatherDetailsFragment : NavigationButtonFragmentUser() {

    private var binding : FragmentWeatherDetailsBinding? = null
    private val args by navArgs<WeatherDetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherDetailsBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationButton()
        val arg = args.weatherData
    }

    override fun setupNavigationButton(){
        activity?.let{ activity ->
            ((activity as NavHolder).getNavPlaceholder() as ToolbarHolder)
                .configureNavigationButtonToAction(WeatherDetailsFragmentDirections.actionWeatherDetailsFragmentToWeatherList())
        }
        findNavController()
    }
}