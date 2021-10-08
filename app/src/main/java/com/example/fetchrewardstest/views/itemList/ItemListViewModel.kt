package com.example.fetchrewardstest.views.itemList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardstest.repository.ItemListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemListRepository: ItemListRepository
) : ViewModel() {

    val data = itemListRepository.items

    init {
        viewModelScope.launch(Dispatchers.IO) {
            itemListRepository.refreshItemList()
        }
    }
}