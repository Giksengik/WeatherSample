package ru.giksengik.weathersample.ui.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.Coordinates
import ru.giksengik.weathersample.repositories.WeatherRepository
import rx.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModelImpl @Inject constructor(private val weatherRepository: WeatherRepository):  ViewModel() {

    private var _viewState : MutableLiveData<WeatherListViewState> = MutableLiveData()

    val viewState : LiveData<WeatherListViewState>
    get() = _viewState

    fun getWeather(){

        weatherRepository.getWeather(listOf(
            Coordinates(lat = 55.33,lon = 37.22),
            Coordinates(lat = 51.30, lon = 0.07),
            Coordinates(lat = -40.0, lon = 74.0)))
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