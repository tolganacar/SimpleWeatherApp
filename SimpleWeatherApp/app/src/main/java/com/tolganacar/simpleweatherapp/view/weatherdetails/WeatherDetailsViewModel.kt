package com.tolganacar.simpleweatherapp.view.weatherdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolganacar.simpleweatherapp.domain.weatherdetails.GetWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    val weatherDetailsUIModel = MutableLiveData<WeatherDetailsUIModel>()

    val shouldShowErrorMessageWeatherDetails = MutableLiveData<Boolean>()
    val isLoadingWeatherDetails = MutableLiveData<Boolean>()

    fun getWeatherDetailsFromAPI(cityID: Int) = viewModelScope.launch {
        weatherDetailsUseCase.execute(GetWeatherDetailsUseCase.Params(cityID))
            .onStart { isLoadingWeatherDetails.value = true }
            .onCompletion { isLoadingWeatherDetails.value = false }
            .catch {
                shouldShowErrorMessageWeatherDetails.value = true
            }
            .collect {
                weatherDetailsUIModel.value = it
            }
    }
}