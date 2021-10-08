package com.example.fetchrewardstest.network

import com.example.fetchrewardstest.data.Constants
import com.example.fetchrewardstest.network.model.NetworkItemListItem
import retrofit2.http.GET

interface ItemListService {

    @GET(Constants.HIRING_JSON)
    suspend fun getItemList(): List<NetworkItemListItem>
}