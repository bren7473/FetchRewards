package com.example.fetchrewardstest.views.itemList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fetchrewardstest.R
import com.example.fetchrewardstest.databinding.FragmentItemListBinding
import com.example.fetchrewardstest.repository.ItemListRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ItemListFragment : Fragment() {
    private val viewModel: ItemListViewModel by viewModels()

    @Inject
    lateinit var adapter: ItemsListAdapter

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    val refreshListener  = SwipeRefreshLayout.OnRefreshListener {
        swipeRefreshLayout.isRefreshing = true
        // call api to reload the screen
        GlobalScope.launch (Dispatchers.Main) { swipeRefresh() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_item_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        adapter.clickListener.onItemClick = {
        }
        swipeRefreshLayout.setOnRefreshListener(refreshListener);



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }


    suspend fun swipeRefresh() {
        Toast.makeText(context, "I'm sure I'm not quite correctly calling this refresh method" +
                " because I'm pretty new to using HILT!", Toast.LENGTH_LONG).show()
        viewModel.refresh()
        swipeRefreshLayout.isRefreshing = false
    }


}