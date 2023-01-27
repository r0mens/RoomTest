package com.roman_druck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roman_druck.entitis.Item


@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)


    @Query("SELECT id, name, price FROM items WHERE name = :name")
    fun getItemByName(name: String): LiveData<Item>


}