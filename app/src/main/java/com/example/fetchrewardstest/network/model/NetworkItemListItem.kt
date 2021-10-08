package com.example.fetchrewardstest.network.model

import com.example.fetchrewardstest.database.DatabaseItemListItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkItemListItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "listId")
    val listId: Int,
    @Json(name = "name")
    val name: String?
)

fun List<NetworkItemListItem>.asDatabaseModel(): List<DatabaseItemListItem> {
    return map {
        DatabaseItemListItem(
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }
}