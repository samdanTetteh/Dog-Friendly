package com.ijikod.dog_friendly.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.dog_friendly.databinding.DetailListItemBinding
import com.squareup.picasso.Picasso

class BreedDetailsAdapter : RecyclerView.Adapter<DetailsViewHolder>() {

    var data = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(DetailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}

class DetailsViewHolder(binding: DetailListItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val detailImageView = binding.detailsImage

    fun bind(breadImg: String){
        Picasso.get().load(breadImg).into(detailImageView)
    }
}