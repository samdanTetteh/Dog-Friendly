package com.ijikod.dog_friendly.allBreeds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsStates
import com.ijikod.dog_friendly.common.VIEW_TYPE_ITEM
import com.ijikod.dog_friendly.common.VIEW_TYPE_SECTION
import com.ijikod.dog_friendly.databinding.BreedListItemBinding
import com.ijikod.dog_friendly.databinding.SubBreedListItemBinding

class BreedListAdapter (
    val showDetails: (String, String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<AllBreedsStates.RecyclerViewItem>()


    override fun getItemViewType(position: Int): Int {
        if (data[position] is AllBreedsStates.SectionItem) {
           return VIEW_TYPE_SECTION
        }

        return VIEW_TYPE_ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SECTION) {
            return BreedViewHolder(BreedListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            )
        }

        return SubBreedViewHolder(SubBreedListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is BreedViewHolder &&  item is AllBreedsStates.SectionItem) {
            holder.bind(item)
        }

        if (holder is SubBreedViewHolder && item is AllBreedsStates.ContentItem) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = data.size


    internal inner class BreedViewHolder(binding: BreedListItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private val breedText = binding.breedTxt

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: AllBreedsStates.SectionItem) {
            breedText.text = item.breed.trim().uppercase()
        }

        override fun onClick(v: View?) {
            showDetails(breedText.text.toString().lowercase(), String())
        }
    }


    internal inner class SubBreedViewHolder(binding: SubBreedListItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private val subBreedText = binding.subBreedTxt
        private var parentBreed = String()

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: AllBreedsStates.ContentItem) {
            subBreedText.text = item.subBreed.trim()
            parentBreed = item.breed.trim().lowercase()
        }

        override fun onClick(v: View?) {
            showDetails(parentBreed, subBreedText.text.toString().lowercase())
        }
    }
}