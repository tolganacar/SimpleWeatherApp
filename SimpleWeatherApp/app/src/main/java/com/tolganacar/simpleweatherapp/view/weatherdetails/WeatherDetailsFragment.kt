package com.tolganacar.simpleweatherapp.view.weatherdetails

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tolganacar.simpleweatherapp.R
import com.tolganacar.simpleweatherapp.databinding.FragmentWeatherDetailsBinding
import com.tolganacar.simpleweatherapp.util.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private val viewModel: WeatherDetailsViewModel by viewModels()

    private lateinit var dataBinding: FragmentWeatherDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentWeatherDetailsBinding?>(
            inflater,
            R.layout.fragment_weather_details,
            container,
            false
        ).apply {
            lifecycleOwner = this@WeatherDetailsFragment
            viewModel = this@WeatherDetailsFragment.viewModel
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        setSwipeRefreshLayout()
        setArguments()
    }

    private fun observeLiveData() {
        viewModel.shouldShowErrorMessageWeatherDetails.observe(viewLifecycleOwner, Observer { error ->
            dataBinding.textErrorWeatherDetails.setVisible(error)
        })

        viewModel.isLoadingWeatherDetails.observe(viewLifecycleOwner, Observer { loading ->
            dataBinding.loadingBarWeatherDetails.setVisible(loading)
        })
    }

    private fun setSwipeRefreshLayout() {
        dataBinding.swipeRefreshLayoutWeatherDetails.setOnRefreshListener {
            dataBinding.textErrorWeatherDetails.visibility = View.GONE
            dataBinding.loadingBarWeatherDetails.visibility = View.VISIBLE
            setArguments()
            dataBinding.swipeRefreshLayoutWeatherDetails.isRefreshing = false
        }
    }

    private fun setArguments() {
        arguments?.let {
            viewModel.getWeatherDetailsFromAPI(WeatherDetailsFragmentArgs.fromBundle(it).cityID)
        }
    }
}