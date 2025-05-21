package com.example.trainingtesttask.presentation.fragments.vacanciesFragment.vacanciesAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.trainingtesttask.data.models.Vacancy

class VacancyDiffUtils : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem == newItem

}
