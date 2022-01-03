package com.ijikod.dog_friendly.allBreeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.composethemeadapter.MdcTheme
import com.ijikod.dog_friendly.R
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


    internal inner class BreedViewHolder(binding: BreedListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val breedText = binding.breedTxt

        fun bind(item: AllBreedsStates.SectionItem) {
            breedText.setContent {
                MdcTheme {
                    BreedTextContent(breed = item.breed.trim().uppercase())
                }
            }
        }

        @Composable
        private fun BreedTextContent(breed: String) {
            Text(
                text = breed,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .clickable {
                        showDetails(breed.lowercase(), String())
                    }
                    .background(Color(R.color.breed_bg_color))
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.breed_txt_padding),
                        vertical = dimensionResource(id = R.dimen.breed_txt_padding)
                    )
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }


    internal inner class SubBreedViewHolder(binding: SubBreedListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val subBreedText = binding.subBreedTxt
        private var parentBreed = String()

        fun bind(item: AllBreedsStates.ContentItem) {
            parentBreed = item.breed.trim().lowercase()
            subBreedText.setContent {
                MdcTheme {
                    SubBreedTextContent(subBreed = item.subBreed.trim())
                }
            }
        }


        @Composable
        private fun SubBreedTextContent(subBreed: String) {
            Text(
                text = subBreed,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .clickable {
                        showDetails(parentBreed, subBreed.lowercase())
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.sub_breed_padding),
                        vertical = dimensionResource(id = R.dimen.sub_breed_padding)
                    )
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }
}