package me.pegbeer.expresate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import me.pegbeer.expresate.databinding.FragmentWeatherBinding

@AndroidEntryPoint
class WeatherFragment : Fragment(){

    private val viewModel:MainViewModel by activityViewModels()
    private val binding:FragmentWeatherBinding by lazy { FragmentWeatherBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val weather = viewModel.weather
        val location = viewModel.citySelected.value
        binding.cityWeatherTv.text = String.format("%s,",location?.name)
        binding.countryWeatherTv.text = location?.country
        Log.d(TAG, "initViews: ${weather?.current?.tempC}")
        binding.temperatureTv.text = String.format("%d°",weather?.current?.tempC?.toInt())
        binding.stateWeatherTv.text = weather?.current?.condition?.text
    }

    companion object{
        const val TAG = "WeatherFragment"
    }

}