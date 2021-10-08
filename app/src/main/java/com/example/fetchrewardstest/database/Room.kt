package com.example.fetchrewardstest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemsDao {

    // item list
    @Query("select * from DatabaseItemListItem  where name is not null and name != '' order by listId asc, name")
    fun getDatabaseItems(): LiveData<List<DatabaseItemListItem>>

    // updates local storage if item is updated
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<DatabaseItemListItem>)
}

@Database(entities = [DatabaseItemListItem::class], version = 3)
abstract class ItemsDatabase : RoomDatabase() {
    abstract val itemsDao: ItemsDao
}