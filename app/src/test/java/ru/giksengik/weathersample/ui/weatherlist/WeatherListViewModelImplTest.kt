package ru.giksengik.weathersample.ui.weatherlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.repositories.WeatherRepository
import ru.giksengik.weathersample.rules.RxSchedulerRule

@RunWith(MockitoJUnitRunner::class)
class WeatherListViewModelImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var initRule: MockitoRule = MockitoJUnit.rule()
    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    @MockK
    private var repository = Mockito.mock(WeatherRepository::class.java)
    private lateinit var viewModel: WeatherListViewModelImpl

    private val weather: MutableList<WeatherData> = mutableListOf()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setupFetchingWeatherData()
        viewModel = WeatherListViewModelImpl(repository)
    }

    private fun setupFetchingWeatherData() {
        addFakeWeather()
        every { repository.getAllWeather() } returns Flowable.just(weather)
    }

    private fun setupUpdatingData() {
        every { repository.loadAllWeather() } returns Single.just(listOf())
    }

    private fun setupDeletingWeather() {
        every {repository.deleteWeather(any())} returns Single.just(Unit)
    }

    private fun addFakeWeather() {
        weather.addAll(
            listOf(
                WeatherData(
                    name = "Ivanovo",
                    lat = 56.99719,
                    lon = 40.97139,
                    region = "Russia",
                    timezone = "",
                    timezoneOffset = 0L,
                    currentWeather = mockk(),
                    hourlyWeather = listOf(),
                    dailyWeather = listOf(),
                    weatherAlerts = listOf()
                ),
                WeatherData(
                    name = "Moscow",
                    lat = 55.75222,
                    lon = 37.61556,
                    region = "Russia",
                    timezone = "",
                    timezoneOffset = 0L,
                    currentWeather = mockk(),
                    hourlyWeather = listOf(),
                    dailyWeather = listOf(),
                    weatherAlerts = listOf()
                )
            )
        )
    }

    @Test
    fun `get all weather EXPECTED correct data`(){
        viewModel.getWeather()
        Assert.assertEquals(WeatherListViewState.Success.Loaded(weather),viewModel.viewState.value)
    }

    @Test
    fun `load weather from source EXPECTED weather updated`() {
        setupUpdatingData()
        viewModel.loadWeather()
        Assert.assertEquals(WeatherListViewState.Success.WeatherUpdated, viewModel.viewState.value)
    }

    @Test
    fun `delete weather EXPECTED weather deleted`() {
        setupDeletingWeather()
        viewModel.deleteWeather(
            WeatherData(0.0,0.0, "", "", "", 0L,
        mockk(), listOf(), listOf(), listOf())
        )
        Assert.assertEquals(WeatherListViewState.Success.WeatherDeleted, viewModel.viewState.value)
    }
}
