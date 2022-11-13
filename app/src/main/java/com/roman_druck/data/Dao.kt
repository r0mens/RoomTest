package com.roman_druck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roman_druck.entitis.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)
    @Query("SELECT * FROM items")
    fun getAllItem(): Flow<List<Item>>
}