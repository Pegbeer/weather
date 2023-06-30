package me.pegbeer.expresate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.pegbeer.expresate.R
import me.pegbeer.expresate.databinding.FragmentHomeBinding
import me.pegbeer.expresate.ui.views.CityAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var citiesAdapter:CityAdapter

    private val viewModel:MainViewModel by activityViewModels()

    private val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    private var searchJob: Job? = null


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
        citiesAdapter = CityAdapter(requireContext())

        (binding.locationsInput.editText as? MaterialAutoCompleteTextView)?.setAdapter(citiesAdapter)

        (binding.locationsInput.editText as? MaterialAutoCompleteTextView)?.setOnItemClickListener { _, _, position, _ ->
            val item = citiesAdapter.getItem(position)
            viewModel.citySelected.postValue(item)
        }

        binding.locationsInput.editText?.doOnTextChanged { text,_,_,_ ->
            viewModel.cityQuery.postValue(text.toString())
        }

        binding.btnGoLogin.setOnClickListener{
            if(viewModel.citySelected.value == null){
                Snackbar.make(binding.root,"Seleccione una locación", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.getWeather()
            parentFragment?.findNavController()?.navigate(R.id.action_homeFragment_to_loginFragment)
        }


        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getCities().buffer().collect{list ->
                val items = list.asSequence().sortedBy { it.name }.toList()
                withContext(Dispatchers.Main){
                    citiesAdapter.setModels(items)
                }
            }
        }
    }

    companion object{
        const val TAG = "HomeFragment"
    }

}