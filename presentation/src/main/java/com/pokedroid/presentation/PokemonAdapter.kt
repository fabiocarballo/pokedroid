package com.pokedroid.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pokedroid.domain.repository.PokemonList


class PokemonAdapter(private var pokemonList: PokemonList): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokemonAdapter.PokemonViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list_item, parent, false) as TextView

        return PokemonViewHolder(textView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.textView.text = pokemonList[position].name
    }

    override fun getItemCount() = pokemonList.size

}