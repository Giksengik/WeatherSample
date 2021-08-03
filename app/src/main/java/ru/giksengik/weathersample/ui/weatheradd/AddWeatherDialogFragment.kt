package ru.giksengik.weathersample.ui.weatheradd

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import ru.giksengik.weathersample.databinding.FragmentAddWeatherBinding

class AddWeatherDialogFragment : DialogFragment() {

    var binding : FragmentAddWeatherBinding? = null

    val viewModel : AddWeatherViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddWeatherBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonCancel?.setOnClickListener{
            dismiss()
        }

    }
}