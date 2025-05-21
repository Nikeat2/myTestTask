package com.example.trainingtesttask.presentation.fragments.vacanciesFragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingtesttask.R
import com.example.trainingtesttask.data.models.Offer
import com.example.trainingtesttask.databinding.FragmentVacanciesBinding
import com.example.trainingtesttask.domain.offersRepository.OnOfferClick
import com.example.trainingtesttask.presentation.fragments.vacanciesFragment.offersadapter.OffersListAdapter
import com.example.trainingtesttask.presentation.fragments.vacanciesFragment.vacanciesAdapter.VacanciesListAdapter
import kotlinx.coroutines.launch

class VacanciesFragment : Fragment(), OnOfferClick {

    private var _binding: FragmentVacanciesBinding? = null
    private val binding get() = _binding!!
    private val offersListAdapter = OffersListAdapter(this)
    private val vacanciesListAdapter = VacanciesListAdapter()

    private val viewModel: VacanciesViewModel by viewModels { VacanciesViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesBinding.inflate(layoutInflater)
        binding.offersRecyclerView.adapter = offersListAdapter
        binding.offersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.vacanciesRecyclerView.adapter = vacanciesListAdapter
        binding.vacanciesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moreVacanciesBtn.setOnClickListener {
            binding.offersRecyclerView.visibility = View.GONE
            binding.moreVacanciesBtn.visibility = View.GONE
            binding.searchImage.setImageResource(R.drawable.favorite_default_icon)
            binding.forYouVacanciesTextView.text =
                formatVacanciesTextForSecondState(vacanciesListAdapter.currentList.size)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.offers.collect {
                        offersListAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.vacancies.collect {
                        vacanciesListAdapter.submitList(it)
                        binding.moreVacanciesBtn.text = formatVacanciesText(it.size)
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = VacanciesFragment()
    }

    override fun onClick(offer: Offer) {
        val intent = Intent(Intent.ACTION_VIEW, offer.link?.toUri())
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Не удалось открыть ссылку", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getVacancyDeclension(count: Int): String {
        val lastDigit = count % 10
        val lastTwoDigits = count % 100

        return when {
            lastTwoDigits in 11..14 -> "вакансий"
            lastDigit == 1 -> "вакансия"
            lastDigit in 2..4 -> "вакансии"
            else -> "вакансий"
        }
    }

    private fun formatVacanciesText(count: Int): String {
        return "Еще $count ${getVacancyDeclension(count)}"
    }

    private fun formatVacanciesTextForSecondState(count: Int): String {
        return "$count ${getVacancyDeclension(count)}"
    }
}