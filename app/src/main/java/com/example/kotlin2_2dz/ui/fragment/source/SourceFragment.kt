package com.example.kotlin2_2dz.ui.fragment.source

import android.util.Log
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
class SourceFragment : BaseFragment<FragmentSourceBinding, SourcesViewModel>(R.layout.fragment_source) {

    override val binding by viewBinding(FragmentSourceBinding::bind)
    override val viewModel: SourcesViewModel by viewModels()

    private val sourcesAdapter = SourcesCountryUsAdapter()

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
        sourcesAdapter.addLoadStateListener {
            try {
                binding.swipeSourcesCountry.isRefreshing = it.refresh == LoadState.Loading
            } catch (e: IllegalStateException) {
                Log.e("anime", "$e")
            }
        }
    }

    private fun listenerSwipe() {
        binding.swipeSourcesCountry.setOnRefreshListener {
            sourcesAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = sourcesAdapter
    }

    private fun fetchSourcesCountryUs() {
        lifecycleScope.launch {
            viewModel.fetchSourcesCountryUs().collectLatest {
                sourcesAdapter.submitData(it)
            }
        }
    }
}