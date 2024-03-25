package com.example.flickfusion.presentation.ui.fragments.search

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flickfusion.R
import com.example.flickfusion.base.BaseFragment
import com.example.flickfusion.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val binding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel: SearchViewModel by viewModels()

}