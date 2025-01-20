package com.example.testapp.data.local

import com.example.testapp.domain.model.EntryViewData
import com.example.testapp.domain.repository.EntryRepository

class EntryRepositoryImpl(private val entryDao: EntryDao) : EntryRepository {

    override suspend fun addEntry(entry: EntryViewData) {
        val entryEntity = Entry(
            name = entry.name,
            description = entry.description
        )
        entryDao.insert(entryEntity)
    }

    override suspend fun getEntries(): List<EntryViewData> {
        val entryEntities = entryDao.getAllEntries()
        return entryEntities.map {
            EntryViewData(
                name = it.name,
                description = it.description,
                wordCount = it.description.split(" ").size
            )
        }
    }
}