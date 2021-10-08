package com.example.fetchrewardstest.network

import com.example.fetchrewardstest.network.model.NetworkItemListItem
import retrofit2.http.GET

interface ItemListService {

    @GET("/hiring.json")
    suspend fun getItemList(): List<NetworkItemListItem>
}