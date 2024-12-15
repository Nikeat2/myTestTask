package com.example.myapplicationg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MyAdapter (private var characterList: MutableList<RMCharacter>) : RecyclerView.Adapter<MyAdapter.ViewHolder> () {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterImage: ImageView = view.findViewById(R.id.ivCharacter)
        val characterNameTv: TextView = view.findViewById(R.id.characterName)
        val characterSpeciesTv: TextView = view.findViewById(R.id.characterSpecies)
        val characterStatusTv: TextView = view.findViewById(R.id.characterStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
       val character = characterList[position]
        holder.characterNameTv.text = character.name
        holder.characterSpeciesTv.text = character.species
        holder.characterStatusTv.text = character.status
        Glide.with(holder.itemView.context).load(character.image).centerCrop().into(holder.characterImage)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}