package com.tolganacar.simpleweatherapp.view.weathercitylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolganacar.simpleweatherapp.data.model.weathercitylist.toUIModel
import com.tolganacar.simpleweatherapp.domain.weathercitylist.GetCityListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherCityListViewModel @Inject constructor(
    private val getCityListUseCase: GetCityListUseCase,
) : ViewModel() {

    val cityListUIModel = MutableLiveData<CityListUIModel>()

    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getWeatherCityList() = viewModelScope.launch {
        getCityListUseCase.execute(Unit)
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                cityListUIModel.value = it.toUIModel()
            }
    }
}