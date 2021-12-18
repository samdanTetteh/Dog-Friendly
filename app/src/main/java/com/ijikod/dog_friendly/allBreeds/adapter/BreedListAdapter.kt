package com.ijikod.dog_friendly.allBreeds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.dog_friendly.R
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsState

const val VIEW_TYPE_SECTION = 1
const val VIEW_TYPE_ITEM = 2

class BreedListAdapter (
    val showSubBreeds: (String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<AllBreedsState.RecyclerViewItem>()


    override fun getItemViewType(position: Int): Int {
        if (data[position] is AllBreedsState.SectionItem) {
           return VIEW_TYPE_SECTION
        }

        return VIEW_TYPE_ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SECTION) {
            return BreedViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.breed_list_item, parent, false)
            )
        }

        return SubBreedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sub_breed_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is BreedViewHolder &&  item is AllBreedsState.SectionItem) {
            holder.bind(item)
        }

        if (holder is SubBreedViewHolder && item is AllBreedsState.ContentItem) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = data.size


    internal inner class BreedViewHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private val breedText = view.findViewById<TextView>(R.id.breed_txt)

        fun bind(item: AllBreedsState.SectionItem) {
            breedText.text = item.title
        }

        override fun onClick(v: View?) {
//          showSubBreeds(selectedItem?.keys?.first() ?: "")
        }
    }


    internal inner class SubBreedViewHolder(view: View): RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private val subBreedText = view.findViewById<TextView>(R.id.sub_breed_txt)

        fun bind(item: AllBreedsState.ContentItem) {
            subBreedText.text = item.name
        }

        override fun onClick(v: View?) {
//          showSubBreeds(selectedItem?.keys?.first() ?: "")
        }
    }
}