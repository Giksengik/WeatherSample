package ru.giksengik.weathersample.ui

import androidx.navigation.NavDirections

interface ToolbarHolder {

    fun configureNavigationButtonToAction(action : NavDirections)

    fun configureNavigationButtonToShowDrawer()
}