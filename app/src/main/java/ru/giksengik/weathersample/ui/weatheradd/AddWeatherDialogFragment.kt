package ru.giksengik.weathersample.ui.weatheradd


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.FragmentAddWeatherBinding
import ru.giksengik.weathersample.extensions.exhaustive
import ru.giksengik.weathersample.models.LocationData
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AddWeatherDialogFragment : DialogFragment() {

    var binding : FragmentAddWeatherBinding? = null

    private val viewModel : AddWeatherViewModel by viewModels()
    private val disposables = CompositeDisposable()
    private var currentGeoQueryResults : List<LocationData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWeatherBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonCancel?.setOnClickListener{
            dismiss()
        }

        binding?.weatherPlaceField?.apply{

            disposables.add(
                this.textChanges()
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                    viewModel.loadQueryResults(it.toString())
                })
            disposables.add(
                this.textChanges()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        onTextChange()
                    }
            )

        }

        binding?.buttonOk?.setOnClickListener{
            if(currentGeoQueryResults != null && currentGeoQueryResults!!.isNotEmpty())
                viewModel.addWeatherLocation(currentGeoQueryResults!![0])
            else
                onIncorrectInput()
        }

        viewModel.viewState.observe(viewLifecycleOwner){ viewState ->
            handleState(viewState)
        }
    }

    private fun onIncorrectInput() {
        binding?.weatherPlaceBlock?.helperText = getString(R.string.incorrent_input)
    }

    private fun onTextChange() {
        binding?.buttonOk?.isEnabled = false
        clearHelpers()
    }

    private fun clearHelpers() {
        binding?.weatherPlaceBlock?.helperText = ""
    }

    private fun handleState(viewState: AddWeatherViewState?) =
        when(viewState){
            is AddWeatherViewState.LocationsLoaded -> onLocationsLoaded(viewState.results)
            is AddWeatherViewState.Loading -> onLoading()
            is AddWeatherViewState.WeatherPlaceAdded -> onWeatherPlaceAdded()
            is AddWeatherViewState.Error.NetworkError -> onNetworkError()
            is AddWeatherViewState.Error.HttpError -> onHttpError()
            else  -> onPlaceAlreadyWritten()
        }

    private fun onLoading() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.weatherPlaceBlock?.isEnabled = false
        binding?.buttonOk?.isEnabled = false
        binding?.buttonCancel?.isEnabled = false
    }

    private fun onLoaded(){
        binding?.progressBar?.visibility = View.GONE
        binding?.weatherPlaceBlock?.isEnabled = true
        binding?.buttonCancel?.isEnabled = true
    }

    private fun onLocationsLoaded(results: List<LocationData>) {
        onLoaded()
        binding?.buttonOk?.isEnabled = true
        currentGeoQueryResults = results.toSet().toList()
    }

    private fun onWeatherPlaceAdded()  {
        dismiss()
    }

    private fun onPlaceAlreadyWritten(){
        onLoaded()
        binding?.weatherPlaceBlock?.helperText = getString(R.string.place_already_written_error_text)

    }

    private fun onHttpError()  {
        onLoaded()
        binding?.weatherPlaceBlock?.helperText = getString(R.string.http_error_message)
    }

    private fun onNetworkError()  {
        onLoaded()
        binding?.weatherPlaceBlock?.helperText = getString(R.string.network_error_message)
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
        binding = null
        currentGeoQueryResults = null
    }
}