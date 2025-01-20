package com.example.testapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {

    @Insert
    suspend fun insert(entry: Entry)

    @Query("SELECT * FROM entries")
    suspend fun getAllEntries(): List<Entry>
}