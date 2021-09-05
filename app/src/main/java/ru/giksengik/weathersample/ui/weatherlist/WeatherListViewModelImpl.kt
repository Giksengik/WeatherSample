package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.repositories.WeatherRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private var _viewState: MutableLiveData<WeatherListViewState> = MutableLiveData()
    val viewState: LiveData<WeatherListViewState>
        get() = _viewState

    private val disposables = CompositeDisposable()

    init {
        getWeather()
    }

    fun getWeather() {
        _viewState.value = WeatherListViewState.Loading
        disposables.add(weatherRepository.getAllWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _viewState.postValue(WeatherListViewState.Success.Loaded(it))
            }
        )
    }

    fun loadWeather() =
        disposables.add(
            weatherRepository.loadAllWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _viewState.value = WeatherListViewState.Success.WeatherUpdated
                    },
                    {
                        it.convertToViewState()
                    }
                )
        )


    fun deleteWeather(weatherData: WeatherData) =
        disposables.add(
            weatherRepository.deleteWeather(weatherData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() )
            .subscribe(
                {
                    _viewState.value = WeatherListViewState.Success.WeatherDeleted
                },
                {}
            )
        )

    fun clear(){
        disposables.clear()
    }

    private fun Throwable.convertToViewState() =
        when (this) {
            is IOException -> WeatherListViewState.Error.NetworkError
            else -> WeatherListViewState.Error.HttpError
        }

}