package com.example.exampletransferdatafromfragmentatofragmentb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RMCharacterAdapter(private var characterList: MutableList<RMCharacter>) :
    RecyclerView.Adapter<RMCharacterAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterName: TextView = view.findViewById(R.id.characterName)
        val characterSpecies: TextView = view.findViewById(R.id.characterSpecies)
        val characterImage: ImageView = view.findViewById(R.id.ivCharacter)
        val characterGender: TextView = view.findViewById(R.id.characterGender)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RMCharacterAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RMCharacterAdapter.ViewHolder, position: Int) {
       val character = characterList[position]
        holder.characterGender.text = character.gender
        holder.characterName.text = character.name
        holder.characterSpecies.text = character.species

    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}