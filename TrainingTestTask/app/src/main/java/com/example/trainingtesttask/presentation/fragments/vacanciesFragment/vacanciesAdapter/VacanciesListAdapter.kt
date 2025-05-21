package com.example.trainingtesttask.presentation.fragments.vacanciesFragment.vacanciesAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trainingtesttask.R
import com.example.trainingtesttask.data.models.Vacancy
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class VacanciesListAdapter :
    ListAdapter<Vacancy, VacanciesListAdapter.VacancyViewHolder>(VacancyDiffUtils()) {

    class VacancyViewHolder(view: View) : ViewHolder(view) {
        val lookingNumber: TextView = view.findViewById(R.id.lookingNumberTextView)
        val favoriteButton: ImageButton = view.findViewById(R.id.isFavoriteBtn)
        val vacancyTitle: TextView = view.findViewById(R.id.vacancyTitleTextView)
        val vacancyTown: TextView = view.findViewById(R.id.townTextView)
        val company: TextView = view.findViewById(R.id.companyTextView)
        val experience: TextView = view.findViewById(R.id.experienceTextView)
        val publishedDate: TextView = view.findViewById(R.id.publishedDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.vacancies_recycler_view_item_layout, parent, false)
        return VacancyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.vacancyTitle.text = vacancy.title
        holder.vacancyTown.text = vacancy.address.town
        holder.company.text = vacancy.company
        holder.lookingNumber.text = formatLookingText(vacancy.lookingNumber)
        holder.favoriteButton.setImageResource(
            when (vacancy.isFavorite) {
                true -> R.drawable.favorite_is_true_icon
                else -> R.drawable.favorite_default_icon
            }
        )
        holder.experience.text = vacancy.experience.previewText
        holder.publishedDate.text = vacancy.publishedDate.formatVacancyDate()
    }

    private fun String.formatVacancyDate(): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(this) ?: return this

            val day = SimpleDateFormat("d", Locale.getDefault()).format(date)
            val month = getDeclinedMonth(date)

            "Опубликовано $day $month"
        } catch (e: Exception) {
            this
        }
    }

    private fun getDeclinedMonth(date: Date): String {
        val calendar = Calendar.getInstance().apply { time = date }
        return when (calendar.get(Calendar.MONTH)) {
            Calendar.JANUARY -> "января"
            Calendar.FEBRUARY -> "февраля"
            Calendar.MARCH -> "марта"
            Calendar.APRIL -> "апреля"
            Calendar.MAY -> "мая"
            Calendar.JUNE -> "июня"
            Calendar.JULY -> "июля"
            Calendar.AUGUST -> "августа"
            Calendar.SEPTEMBER -> "сентября"
            Calendar.OCTOBER -> "октября"
            Calendar.NOVEMBER -> "ноября"
            Calendar.DECEMBER -> "декабря"
            else -> ""
        }
    }

    private fun getPeopleDeclension(count: Int?): String {
        if (count == null) return "" // Если число null - возвращаем пустую строку

        val lastDigit = count % 10
        val lastTwoDigits = count % 100

        return when {
            lastTwoDigits in 11..14 -> "человек"
            lastDigit == 1 -> "человек"
            lastDigit in 2..4 -> "человека"
            else -> "человек"
        }
    }

    private fun formatLookingText(lookingNumber: Int?): String {
        return lookingNumber?.let {
            "Сейчас просматривает $it ${getPeopleDeclension(it)}"
        } ?: ""
    }
}