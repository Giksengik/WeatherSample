package ru.giksengik.weathersample.ui.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.giksengik.weathersample.databinding.FragmentWeatherListBinding


@AndroidEntryPoint
class WeatherList : Fragment() {


    private val viewModel by viewModels<WeatherListViewModelImpl>()

    private var binding : FragmentWeatherListBinding? = null
    private var listAdapter : WeatherListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentWeatherListBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listAdapter = WeatherListAdapter()
        binding?.weatherList?.apply{
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.viewState.observe(viewLifecycleOwner){
            handleState(it)
        }


        viewModel.getWeather()
    }

    private fun handleState(viewState : WeatherListViewState) =
        when(viewState){
            is WeatherListViewState.Success.Loaded -> {
                setLoaded()
                listAdapter?.submitList(viewState.weatherList)
            }
            is WeatherListViewState.Loading -> setLoading()
            is WeatherListViewState.Error.HttpError -> onHttpError()
            is WeatherListViewState.Error.NetworkError -> onNetworkError()

        }

    private fun setLoading() {
        binding?.weatherListProgressBar?.visibility = View.VISIBLE
    }

    private fun setLoaded() {
        binding?.weatherListProgressBar?.visibility = View.GONE
    }

    private fun onNetworkError() {
        setLoaded()

    }

    private fun onHttpError() {
        setLoaded()

    }

    override fun onDestroy() {
        super.onDestroy()
        listAdapter = null
        binding = null
    }

}