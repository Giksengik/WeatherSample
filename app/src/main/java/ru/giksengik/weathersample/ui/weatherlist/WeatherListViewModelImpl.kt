package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.repositories.WeatherRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(private val weatherRepository: WeatherRepository):  ViewModel() {

    private var _viewState : MutableLiveData<WeatherListViewState> = MutableLiveData()

    val viewState : LiveData<WeatherListViewState>
    get() = _viewState

    init{
        getWeather()
    }


    private fun getWeather(){
        _viewState.value = WeatherListViewState.Loading
        weatherRepository.getAllWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext{
                    _viewState.postValue(WeatherListViewState.Success.Loaded(it))
                }
                .subscribe()
    }


    fun loadWeather(){
        weatherRepository.loadAllWeather()
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

    fun deleteWeather(weatherData: WeatherData){
        Single.create<Nothing> {
            weatherRepository.deleteWeather(weatherData)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }


}