package com.example.fetchrewardstest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.fetchrewardstest.database.ItemsDatabase
import com.example.fetchrewardstest.database.asDomainModel
import com.example.fetchrewardstest.domain.ItemListItem
import com.example.fetchrewardstest.network.ItemListService
import com.example.fetchrewardstest.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class ItemListRepository @Inject constructor(
    private val itemListService: ItemListService,
    private val database: ItemsDatabase
) {
    val items: LiveData<List<ItemListItem>> =
        Transformations.map(database.itemsDao.getDatabaseItems()) {
            it.asDomainModel()
        }

    suspend fun refreshItemList() {
        try {
            val items = itemListService.getItemList()
            database.itemsDao.insertAll(items.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}