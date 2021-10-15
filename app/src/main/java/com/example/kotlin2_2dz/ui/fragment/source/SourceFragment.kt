package com.example.kotlin2_2dz.ui.fragment.source

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_2dz.R
import com.example.kotlin2_2dz.base.BaseFragment
import com.example.kotlin2_2dz.databinding.FragmentSourceBinding
import com.example.kotlin2_2dz.ui.adapter.SourcesCountryUsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourceFragment: BaseFragment<FragmentSourceBinding, SourcesViewModel>(R.layout.fragment_source) {

    override val binding by viewBinding(FragmentSourceBinding::bind)
    override val viewModel: SourcesViewModel by viewModels()

    private val everythingAdapter = SourcesCountryUsAdapter()

    override fun initialize() {
        viewModel.fetchSourcesCountryUs()
    }

    override fun setupRequests() {
        fetchSourcesCountryUs()
    }

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        loadStateListener()
        listenerSwipe()
    }

    private fun loadStateListener() {
        everythingAdapter.addLoadStateListener {
            binding.swipeSourcesCountry.isRefreshing = it.refresh == LoadState.Loading
        }
    }

    private fun listenerSwipe() {
        binding.swipeSourcesCountry.setOnRefreshListener {
            everythingAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun fetchSourcesCountryUs() {
        lifecycleScope.launch {
            viewModel.fetchSourcesCountryUs().collectLatest {
                everythingAdapter.submitData(it)
            }
        }
    }
}