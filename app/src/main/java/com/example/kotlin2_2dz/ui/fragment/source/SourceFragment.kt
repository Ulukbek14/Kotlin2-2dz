package com.example.kotlin2_2dz.ui.fragment.source

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_2dz.R
import com.example.kotlin2_2dz.base.BaseFragment
import com.example.kotlin2_2dz.databinding.FragmentSourceBinding
import com.example.kotlin2_2dz.domain.model.Sources
import com.example.kotlin2_2dz.extensions.scrollListenerUploadNextPage
import com.example.kotlin2_2dz.state.UIState
import com.example.kotlin2_2dz.ui.activity.MainActivity
import com.example.kotlin2_2dz.ui.adapter.SourcesCountryUsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceFragment : BaseFragment<FragmentSourceBinding, SourcesViewModel>(R.layout.fragment_source) {

    override val binding by viewBinding(FragmentSourceBinding::bind)
    override val viewModel: SourcesViewModel by viewModels()
    private val sourcesAdapter = SourcesCountryUsAdapter()

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
        onScrollListener()
    }

    override fun setupObserves() {
        subscribeToSources()
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = sourcesAdapter
    }

    private fun onScrollListener() {
        binding.rv.scrollListenerUploadNextPage(viewModel)
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.rv.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToSources() {
        viewModel.sourcesState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                    binding.swipeSourcesCountry.isRefreshing = true
                }
                is UIState.Error -> {
                    Log.e("anime", it.error)
                }
                is UIState.Success -> {
                    binding.swipeSourcesCountry.isRefreshing = false
                    val dataList = ArrayList<Sources>(sourcesAdapter.currentList)
                    it.data?.let { data -> dataList.addAll(data) }
                    sourcesAdapter.submitList(dataList)
                }
            }
        })
    }
}