package ru.giksengik.weathersample.ui.weatheradd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
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
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.repositories.WeatherRepository
import ru.giksengik.weathersample.rules.RxSchedulerRule
import java.lang.IllegalArgumentException

@RunWith(MockitoJUnitRunner::class)
class AddWeatherViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var initRule: MockitoRule = MockitoJUnit.rule()
    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    @MockK
    private var weatherRepository = Mockito.mock(WeatherRepository::class.java)
    private lateinit var viewModel: AddWeatherViewModel

    private val weather: MutableList<WeatherData> = mutableListOf()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = AddWeatherViewModel(weatherRepository)
    }

    private fun addFakeWeatherData() =
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


    private fun getFakeLocation() = listOf(
        LocationData(
            name = "Ivanovo",
            lat = 56.99719,
            lon = 40.97139,
            country = "Russia"
        )
    )

    private fun setupFakeLocations() {
        every { weatherRepository.getLocationsByQuery(any()) } returns Single.just(listOf())
        every { weatherRepository.getLocationsByQuery(eq("Ivanovo")) } returns Single.just(
            getFakeLocation()
        )
    }

    private fun setupFakeWeather() {
        addFakeWeatherData()
        every { weatherRepository.getAllWeather() } returns Flowable.just(weather)
    }

    private fun setupWeatherAdding() {
        setupFakeWeather()
        val slot = slot<LocationData>()
        every{weatherRepository.addWeatherLocation(capture(slot))} answers {
            if(weather.find { it.name == slot.captured.name } != null)
                Single.error(IllegalArgumentException(""))
            else {
                weather.add(slot.captured.toWeatherData())
                Single.just(slot.captured.toWeatherData())
            }
        }
    }

    @Test
    fun `get locations by correct query EXPECTED correct response`() {
        setupFakeLocations()
        viewModel.loadQueryResults("Ivanovo")
        Assert.assertEquals(
            AddWeatherViewState.LocationsLoaded(getFakeLocation()),
            viewModel.viewState.value
        )
    }

    @Test
    fun `get location by incorrect query EXPECTED empty response`() {
        setupFakeLocations()
        viewModel.loadQueryResults("ojsdgpsgopsg")
        Assert.assertEquals(
            AddWeatherViewState.LocationsLoaded(emptyList()),
            viewModel.viewState.value
        )
    }

    @Test
    fun `add new weather location EXPECTED location is saved`() {
        setupWeatherAdding()
        viewModel.addWeatherLocation(
            LocationData(
                "New Place",
                0.1,
                0.1,
                "counry"
            )
        )
        Assert.assertEquals(AddWeatherViewState.WeatherPlaceAdded, viewModel.viewState.value)
    }

    @Test
    fun `add existing weather location EXPECTED location is not saved`(){
        setupWeatherAdding()
        viewModel.addWeatherLocation(
            LocationData(
                "Ivanovo",
                0.1,
                0.1,
                "counry"
            )
        )
        Assert.assertEquals(AddWeatherViewState.Error.PlaceAlreadyWritten, viewModel.viewState.value)
    }



    private fun LocationData.toWeatherData() : WeatherData =
        WeatherData(
            name = this.name,
            lat = this.lat,
            lon = this.lon,
            region = this.country,
            timezone = "",
            timezoneOffset = 0L,
            currentWeather = mockk(),
            hourlyWeather = listOf(),
            dailyWeather = listOf(),
            weatherAlerts = listOf()
        )
}