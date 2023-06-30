package me.pegbeer.expresate.ui.views

import androidx.recyclerview.widget.DiffUtil
import com.pegbeer.domain.model.Location

class CityDiffCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}