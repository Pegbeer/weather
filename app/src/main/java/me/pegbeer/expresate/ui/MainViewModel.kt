package me.pegbeer.expresate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.pegbeer.domain.model.City
import com.pegbeer.usecases.cities.ISaveCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val citiesUseCase: ISaveCitiesUseCase
) : ViewModel() {

    val cityQuery = MutableLiveData<String>()

    val citySelected = MutableLiveData<City>()

    fun loadCities(){
        viewModelScope.launch(Dispatchers.IO) {
            citiesUseCase.save()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getCities() = cityQuery.asFlow().buffer().flatMapLatest {
        citiesUseCase.getCities(it)
    }



}