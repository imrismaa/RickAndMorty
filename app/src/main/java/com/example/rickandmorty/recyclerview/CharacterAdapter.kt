package com.example.rickandmorty.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.databinding.ItemRickAndMortyBinding
import com.example.rickandmorty.model.RickAndMorty

class CharacterAdapter (private val characterList: List<RickAndMorty>):
    RecyclerView.Adapter<CharacterAdapter.ItemCharacterViewHolder>(){

    inner class ItemCharacterViewHolder(private val binding: ItemRickAndMortyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chrData: RickAndMorty) {
            with(binding){
                val placeholder = android.R.drawable.ic_menu_gallery
                chrName.text = chrData.name
                chrSpecies.text = "Species: " + chrData.species
                chrGender.text = "Gender: " + chrData.gender
                chrStatus.text = "Status: " + chrData.status
                Glide.with(itemView.context).asBitmap().load(chrData.image)
                    .transition(BitmapTransitionOptions.withCrossFade()).apply(
                        RequestOptions().placeholder(placeholder).centerCrop()
                    ).into(chrImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCharacterViewHolder {
        val binding = ItemRickAndMortyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemCharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: ItemCharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }
}