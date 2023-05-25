package com.tolganacar.simpleweatherapp.view.weathercitylist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.simpleweatherapp.databinding.CityListRecyclerRowBinding

class WeatherCityListAdapter(
    private val cityList: ArrayList<CityUI>
) : RecyclerView.Adapter<WeatherCityListAdapter.WeatherCityListHolder>() {

    private var listener: CityClickListener? = null

    class WeatherCityListHolder(val binding: CityListRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCityListHolder {
        val binding =
            CityListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherCityListHolder(binding)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: WeatherCityListHolder, position: Int) {
        holder.binding.city = cityList[position]

        holder.binding.cityName.setOnClickListener {
            listener?.onCityClicked(cityList[position].cityID)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCityList(newCityList: List<CityUI>) {
        cityList.clear()
        cityList.addAll(newCityList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: CityClickListener) {
        this.listener = listener
    }
}