package me.pegbeer.expresate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.pegbeer.expresate.databinding.FragmentHomeBinding
import me.pegbeer.expresate.ui.MainViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel:MainViewModel by activityViewModels()

    private val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }


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
        binding.locationsInput.editText!!.doOnTextChanged{ text,_,_,_ ->
            viewModel.cityQuery.postValue(text.toString())
        }


        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getCities().collect{
                val items = it.map { it.name }.toTypedArray()
                withContext(Dispatchers.Main){
                    (binding.locationsInput.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
                }
            }
        }
    }

}