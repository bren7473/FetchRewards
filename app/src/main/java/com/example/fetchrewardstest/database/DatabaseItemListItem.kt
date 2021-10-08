package com.example.fetchrewardstest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fetchrewardstest.domain.ItemListItem

@Entity
data class DatabaseItemListItem constructor(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val listId: Int,
    val name: String?
)

fun List<DatabaseItemListItem>.asDomainModel(): List<ItemListItem> {
    return map {
        ItemListItem(
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }
}