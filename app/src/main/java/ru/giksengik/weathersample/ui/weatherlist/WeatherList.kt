package ru.giksengik.weathersample.ui.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.giksengik.weathersample.databinding.FragmentWeatherListBinding


@AndroidEntryPoint
class WeatherList : Fragment() {

    private var binding : FragmentWeatherListBinding? = null
    private val viewModel by viewModels<WeatherListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentWeatherListBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}