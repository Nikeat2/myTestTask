package com.example.trainingtesttask.presentation.fragments.vacanciesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingtesttask.R
import com.example.trainingtesttask.databinding.FragmentVacanciesBinding
import com.example.trainingtesttask.presentation.fragments.vacanciesFragment.offersadapter.OffersListAdapter
import kotlinx.coroutines.launch

class VacanciesFragment : Fragment() {

    private var _binding: FragmentVacanciesBinding? = null
    private val binding get() = _binding!!
    private val offersListAdapter = OffersListAdapter()

    private val viewModel: VacanciesViewModel by viewModels { VacanciesViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesBinding.inflate(layoutInflater)
        binding.offersRecyclerView.adapter = offersListAdapter
        binding.offersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.offers.collect{
                    offersListAdapter.submitList(it)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = VacanciesFragment()
    }
}