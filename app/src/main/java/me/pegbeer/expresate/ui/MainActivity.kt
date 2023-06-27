package me.pegbeer.expresate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.pegbeer.expresate.R
import me.pegbeer.expresate.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onStart() {
        super.onStart()
        viewModel.loadCities()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.locationsInput.editText!!.doOnTextChanged{ text,_,_,_ ->
            viewModel.cityQuery.postValue(text.toString())
        }

        binding.locationsInput.addOnEditTextAttachedListener {

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