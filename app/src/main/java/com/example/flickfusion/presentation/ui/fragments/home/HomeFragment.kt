package com.example.flickfusion.presentation.ui.fragments.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flickfusion.R
import com.example.flickfusion.base.BaseFragment
import com.example.flickfusion.databinding.FragmentHomeBinding
import com.example.flickfusion.presentation.ui.adapters.PopularContentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()
    private val popularContentAdapter = PopularContentAdapter()

    override fun initialize() {
        binding.homeRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = popularContentAdapter
        }
    }

    override fun setupSubscribers() {
        subscribeToPopularContents()
    }

    private fun subscribeToPopularContents() {
        viewModel.fetchPopularContents().collectPaging {
            popularContentAdapter.submitData(it)
        }
    }
}