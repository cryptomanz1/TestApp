package com.example.testapp.domain.repository

import com.example.testapp.domain.model.EntryViewData

/**
 * Interface defining the contract for managing entry data.
 * It provides methods to add entries and retrieve a list of entries.
 */
interface EntryRepository {

    /**
     * Adds a new entry to the data source.
     *
     * @param entry An instance of [EntryViewData] representing the entry to be added.
     */
    suspend fun addEntry(entry: EntryViewData)

    /**
     * Retrieves a list of all entries from the data source.
     *
     * @return A list of [EntryViewData] representing all stored entries.
     */
    suspend fun getEntries(): List<EntryViewData>
}