package ru.giksengik.weathersample.ui.weatheradd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.repositories.WeatherRepository
import java.io.IOException
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class AddWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _viewState: MutableLiveData<AddWeatherViewState> = MutableLiveData()

    val viewState: LiveData<AddWeatherViewState>
        get() = _viewState

    private val disposables = CompositeDisposable()
    private var prevRequest: String = ""

    fun loadQueryResults(query: String) {
        clearRequests()
        if (isQueryCorrect(query)) {
            prevRequest = query
            _viewState.value = AddWeatherViewState.Loading
            disposables.add(
                weatherRepository.getLocationsByQuery(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { locations ->
                            _viewState.value = AddWeatherViewState.LocationsLoaded(locations)
                        },
                        {
                            _viewState.value = it.convertToViewState()
                        }
                    )
            )
        }
    }

    fun addWeatherLocation(locationData: LocationData) {
        disposables.add(
            weatherRepository.addWeatherLocation(locationData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _viewState.value = AddWeatherViewState.WeatherPlaceAdded
                    },
                    {
                        _viewState.value = it.convertToViewState()
                    }
                )
        )
    }

    private fun isQueryCorrect(query: String): Boolean = query.length > 3 && prevRequest != query

    private fun clearRequests() {
        disposables.clear()
    }

    private fun Throwable.convertToViewState() =
        when (this) {
            is IOException -> AddWeatherViewState.Error.NetworkError
            is IllegalArgumentException -> AddWeatherViewState.Error.PlaceAlreadyWritten
            else -> AddWeatherViewState.Error.HttpError
        }

    fun clear(){
        disposables.clear()
    }
}

