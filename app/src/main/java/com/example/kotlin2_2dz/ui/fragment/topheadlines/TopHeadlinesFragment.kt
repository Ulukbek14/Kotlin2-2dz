package com.example.kotlin2_2dz.ui.fragment.topheadlines

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_2dz.R
import com.example.kotlin2_2dz.base.BaseFragment
import com.example.kotlin2_2dz.databinding.FragmentTopHeadlinesBinding
import com.example.kotlin2_2dz.domain.model.EverythingModel
import com.example.kotlin2_2dz.extensions.scrollListenerUploadNextPage
import com.example.kotlin2_2dz.state.UIState
import com.example.kotlin2_2dz.ui.activity.MainActivity
import com.example.kotlin2_2dz.ui.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesFragment : BaseFragment<FragmentTopHeadlinesBinding, TopHeadlinesViewModel>(
    R.layout.fragment_top_headlines
) {

    override val binding by viewBinding(FragmentTopHeadlinesBinding::bind)
    override val viewModel: TopHeadlinesViewModel by viewModels()
    private val everythingAdapter = ArticleAdapter()

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
        onScrollListener()
    }

    override fun setupObserves() {
        subscribeToTopHeadlines()
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun onScrollListener() {
        binding.rv.scrollListenerUploadNextPage(viewModel)
    }

    private fun subscribeToTopHeadlines() {
        viewModel.topHeadlinesState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                    binding.swipeHeadlines.isRefreshing = true
                }
                is UIState.Error -> {
                    Log.e("anime", it.error)
                }
                is UIState.Success -> {
                    binding.swipeHeadlines.isRefreshing = false
                    val dataList = ArrayList<EverythingModel>(everythingAdapter.currentList)
                    it.data?.let { data -> dataList.addAll(data) }
                    everythingAdapter.submitList(dataList)
                }
            }
        })
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.rv.smoothScrollToPosition(0)
        }
    }
}