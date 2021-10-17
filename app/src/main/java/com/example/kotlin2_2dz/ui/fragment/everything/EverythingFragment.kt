package com.example.kotlin2_2dz.ui.fragment.everything

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_2dz.R
import com.example.kotlin2_2dz.base.BaseFragment
import com.example.kotlin2_2dz.databinding.FragmentEverythingBinding
import com.example.kotlin2_2dz.ui.adapter.EverythingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EverythingFragment : BaseFragment<FragmentEverythingBinding, EverythingViewModel>(R.layout.fragment_everything) {

    override val binding by viewBinding(FragmentEverythingBinding::bind)
    override val viewModel: EverythingViewModel by viewModels()
    private val everythingAdapter = EverythingAdapter()

    override fun setupRequests() {
        fetchEverything()
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
            try {
                binding.swipeEverything.isRefreshing = it.refresh == LoadState.Loading
            } catch (e: IllegalStateException) {
                Log.e("anime", "$e")
            }
        }
    }

    private fun listenerSwipe() {
        binding.swipeEverything.setOnRefreshListener {
            everythingAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun fetchEverything() {
        lifecycleScope.launch {
            viewModel.fetchEverything().collectLatest {
                everythingAdapter.submitData(it)
            }
        }
    }
}