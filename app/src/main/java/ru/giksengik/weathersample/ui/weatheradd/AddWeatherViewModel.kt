package ru.giksengik.weathersample.ui.weatheradd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.repositories.WeatherRepository
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddWeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _viewState : MutableLiveData<AddWeatherViewState> = MutableLiveData()

    val viewState : LiveData<AddWeatherViewState>
    get() = _viewState

    private val disposables = CompositeDisposable()
    private var prevRequest : String = ""

    fun loadQueryResults(query : String) {
        clearRequests()
        if(isQueryCorrect(query)) {
            prevRequest = query
            _viewState.value = AddWeatherViewState.Loading
            disposables.add(weatherRepository.getLocationsByQuery(query)
                .doOnSuccess {
                    _viewState.postValue(AddWeatherViewState.LocationsLoaded(it))
                }
                .doOnError {
                    it.printStackTrace()
                    _viewState.postValue(getErrorState(it))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            )
        }
    }


    fun addWeatherLocation(locationData : LocationData) {
        weatherRepository.addWeatherLocation(locationData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess{ state ->
                _viewState.postValue(state)
            }
            .doOnError{ throwable ->
                _viewState.postValue(getErrorState(throwable))
            }
            .subscribe()
    }

    private fun getErrorState(e : Throwable) : AddWeatherViewState =
        when(e){
            is IOException -> AddWeatherViewState.Error.NetworkError
            else -> AddWeatherViewState.Error.HttpError
        }

    private fun clearRequests() {
        disposables.clear()
    }

    private fun isQueryCorrect( query: String) : Boolean = query.length > 3 && prevRequest != query
}
