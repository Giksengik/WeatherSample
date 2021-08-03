package ru.giksengik.weathersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.AndroidEntryPoint
import ru.giksengik.weathersample.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , NetworkUser {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNetworkError() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.network_error_message))
            .setPositiveButton(R.string.ok){
                dialog, _ -> dialog.dismiss()
            }
            .show()
    }
}