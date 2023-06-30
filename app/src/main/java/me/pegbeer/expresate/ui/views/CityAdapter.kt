package me.pegbeer.expresate.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.pegbeer.domain.model.Location
import me.pegbeer.expresate.R
import java.util.Locale

class CityAdapter(context: Context) :
    ArrayAdapter<Location>(context, android.R.layout.simple_dropdown_item_1line) {

    private val models: MutableList<Location> = mutableListOf()

    fun setModels(newModels: List<Location>) {
        models.clear()
        models.addAll(newModels)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return models.size
    }

    override fun getItem(position: Int): Location? {
        return models[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.text_view_city, parent, false)
        }

        val model = getItem(position)

        val textView = itemView?.findViewById<TextView>(R.id.tvCity)
        textView?.text = String.format("%s, %s", model?.name, model?.country)

        return itemView!!
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val query = constraint?.toString()?.lowercase(Locale.getDefault())

                val filteredList = if (query.isNullOrEmpty()) {
                    models
                } else {
                    models.filter { model ->
                        model.name.lowercase(Locale.getDefault()).contains(query)
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                val filteredList = results.values as List<Location>
                clear()
                addAll(filteredList)
                notifyDataSetChanged()
            }
        }
    }
}
