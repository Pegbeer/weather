package me.pegbeer.expresate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.usecases.locations.ILocationsUseCase
import com.pegbeer.usecases.weather.IWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val citiesUseCase: ILocationsUseCase,
    private val weatherUseCase:IWeatherUseCase
) : ViewModel() {

    val cityQuery = MutableLiveData<String>()

    val citySelected = MutableLiveData<Location>()

    var weather:WeatherResponse? = null

    fun getWeather(){
        viewModelScope.launch(Dispatchers.IO) {
            weather = weatherUseCase.getWeather(citySelected.value!!)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getCities() = cityQuery.asFlow().buffer().flatMapLatest {
        citiesUseCase.getLocations(it)
    }



}