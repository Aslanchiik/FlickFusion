package com.example.flickfusion.presentation.ui.fragments.home.detail

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flickfusion.R
import com.example.flickfusion.base.BaseFragment
import com.example.flickfusion.databinding.FragmentDetailHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHomeFragment :
    BaseFragment<FragmentDetailHomeBinding, DetailHomeViewModel>(R.layout.fragment_detail_home) {

    override val binding by viewBinding(FragmentDetailHomeBinding::bind)
    override val viewModel: DetailHomeViewModel by viewModels()

    override fun setupSubscribers() {
//        viewModel.fetchDetailHotel()
    }

}