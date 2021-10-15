package com.example.kotlin2_2dz.ui.fragment.topheadlines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_2dz.R
import com.example.kotlin2_2dz.base.BaseFragment
import com.example.kotlin2_2dz.databinding.FragmentTopHeadlinesBinding
import com.example.kotlin2_2dz.ui.adapter.EverythingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesFragment : BaseFragment<FragmentTopHeadlinesBinding, TopHeadlinesViewModel>(R.layout.fragment_top_headlines) {

    override val binding by viewBinding(FragmentTopHeadlinesBinding::bind)
    override val viewModel: TopHeadlinesViewModel by viewModels()

    private val everythingAdapter = EverythingAdapter()

    override fun initialize() {
        viewModel.fetchTopHeadlines()
    }

    override fun setupRequests() {
        fetchTopHeadlines()
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
            binding.swipeHeadlines.isRefreshing = it.refresh == LoadState.Loading
        }
    }

    private fun listenerSwipe() {
        binding.swipeHeadlines.setOnRefreshListener {
            everythingAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun fetchTopHeadlines() {
        lifecycleScope.launch {
            viewModel.fetchTopHeadlines().collectLatest {
                everythingAdapter.submitData(it)
            }
        }
    }
}