package com.tolganacar.simpleweatherapp.view.weathercitylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.simpleweatherapp.databinding.FragmentWeatherCityListBinding
import com.tolganacar.simpleweatherapp.util.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherCityListFragment : Fragment(), CityClickListener {

    private val viewModel: WeatherCityListViewModel by viewModels()

    private lateinit var viewBinding: FragmentWeatherCityListBinding
    private val weatherCityListAdapter = WeatherCityListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentWeatherCityListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerview()
        observeLiveData()
        setSwipeRefreshLayout()

        viewModel.getWeatherCityList()
    }

    private fun initializeRecyclerview() {
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weatherCityListAdapter.apply {
                setOnClickListener(this@WeatherCityListFragment)
            }
        }
    }

    private fun observeLiveData() {
        viewModel.cityListUIModel.observe(viewLifecycleOwner, Observer { cityList ->
            cityList?.let {
                weatherCityListAdapter.updateCityList(cityList.weatherCityList)
            }
        })

        viewModel.shouldShowErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            viewBinding.textErrorCityList.setVisible(error)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            viewBinding.loadingBarCityList.setVisible(loading)
        })
    }

    private fun setSwipeRefreshLayout() {
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            viewBinding.textErrorCityList.visibility = View.GONE
            viewBinding.loadingBarCityList.visibility = View.VISIBLE
            viewModel.getWeatherCityList()
            viewBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCityClicked(cityID: Int) {
        val action =
            WeatherCityListFragmentDirections.actionWeatherCityListFragmentToWeatherDetailsFragment(
                cityID
            )
        findNavController().navigate(action)
    }
}