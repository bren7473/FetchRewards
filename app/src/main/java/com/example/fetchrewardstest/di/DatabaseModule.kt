package com.example.fetchrewardstest.di

import android.content.Context
import androidx.room.Room
import com.example.fetchrewardstest.database.ItemsDao
import com.example.fetchrewardstest.database.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ItemsDatabase {
        return Room.databaseBuilder(
            appContext,
            ItemsDatabase::class.java,
            "Items"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(itemsDatabase: ItemsDatabase): ItemsDao {
        return itemsDatabase.itemsDao
    }
}