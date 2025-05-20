package com.example.trainingtesttask.presentation.fragments.vacanciesFragment.offersadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trainingtesttask.R
import com.example.trainingtesttask.data.models.Offer

class OffersListAdapter :
    ListAdapter<Offer, OffersListAdapter.OffersViewHolder>(OffersDiffUtils()) {

    class OffersViewHolder(view: View) : ViewHolder(view) {
        val offerImage: ImageView = view.findViewById(R.id.itemImage)
        val offerTitle: TextView = view.findViewById(R.id.itemTitleTextView)
        val offerText: TextView = view.findViewById(R.id.offerText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.offers_recycler_view_item_layout, parent, false)
        return OffersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = getItem(position)
        holder.offerText.text = offer.button?.text ?: ""
        holder.offerTitle.text = offer.title
        holder.offerImage.setImageResource(
            when (offer.id) {
                "near_vacancies" -> R.drawable.near_vacancies_icon
                "level_up_resume" -> R.drawable.level_up_resume_icon
                else -> R.drawable.temporary_job_icon
            }
        )
    }
}