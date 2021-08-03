package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import ru.giksengik.weathersample.network.request.LocationRequestData
import ru.giksengik.weathersample.repositories.WeatherRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(private val weatherRepository: WeatherRepository):  ViewModel() {

    private var _viewState : MutableLiveData<WeatherListViewState> = MutableLiveData()

    val viewState : LiveData<WeatherListViewState>
    get() = _viewState

    fun getWeather(){
        weatherRepository.getAllWeather()
            .doOnNext{
                _viewState.postValue(WeatherListViewState.Success.Loaded(it))
            }
            .doOnError {
                it.printStackTrace()
                when(it){
                    is IOException -> WeatherListViewState.Error.NetworkError
                    else -> WeatherListViewState.Error.HttpError
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()

    }
}