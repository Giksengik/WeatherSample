package ru.giksengik.weathersample.ui.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.giksengik.weathersample.NavHolder
import ru.giksengik.weathersample.NetworkUser
import ru.giksengik.weathersample.databinding.FragmentWeatherListBinding
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.ui.NavigationButtonFragmentUser
import ru.giksengik.weathersample.ui.ToolbarHolder
import ru.giksengik.weathersample.ui.weatheradd.AddWeatherDialogFragment


@AndroidEntryPoint
class WeatherList : NavigationButtonFragmentUser() {


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

        setupNavigationButton()

        listAdapter = WeatherListAdapter(object : WeatherListAdapter.OnWeatherClickListener{
            override fun onClick(weatherData: WeatherData) {
                navigateToWeatherDetails(weatherData)
            }
        }
        )

        val onTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(context,viewModel,listAdapter))

        binding?.weatherList?.apply{
            onTouchHelper.attachToRecyclerView(this)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding?.addWeatherButton?.setOnClickListener{
            openAddWeatherPlaceDialog()
        }

        viewModel.viewState.observe(viewLifecycleOwner){
            handleState(it)
        }

        viewModel.loadWeather()
    }

    override fun setupNavigationButton(){
        activity?.let{ activity ->
            ((activity as NavHolder).getNavPlaceholder() as ToolbarHolder)
                .configureNavigationButtonToShowDrawer()
        }
    }

    private fun navigateToWeatherDetails(weatherData: WeatherData) {
        val action = WeatherListDirections.actionWeatherListToWeatherDetailsFragment(weatherData)
        findNavController().navigate(action)
    }


    private fun openAddWeatherPlaceDialog() {
        AddWeatherDialogFragment().show(
                childFragmentManager, "ADD_PLACE"
        )
    }

    private fun handleState(viewState : WeatherListViewState) {
        when (viewState) {
            is WeatherListViewState.Success.Loaded -> {
                setLoaded()
                listAdapter?.submitList(viewState.weatherList)
            }
            is WeatherListViewState.Loading -> setLoading()
            is WeatherListViewState.Error.HttpError -> onHttpError()
            is WeatherListViewState.Error.NetworkError -> onNetworkError()
        }
    }

    private fun setLoading() {
        binding?.weatherListProgressBar?.visibility = View.VISIBLE
    }

    private fun setLoaded() {
        binding?.weatherListProgressBar?.visibility = View.GONE
    }

    private fun onNetworkError() {
        setLoaded()
        activity?.let{
            (it as NetworkUser).onNetworkError()
        }
    }

    private fun onHttpError() {
        setLoaded()
        Toast.makeText(requireContext(), "Http error, please try again", Toast.LENGTH_LONG ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        listAdapter = null
        binding = null
        viewModel.clear()
    }

}
