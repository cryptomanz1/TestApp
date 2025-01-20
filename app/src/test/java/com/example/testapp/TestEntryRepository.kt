package com.example.testapp

import com.example.testapp.data.local.Entry
import com.example.testapp.data.local.EntryDao
import com.example.testapp.data.local.EntryRepositoryImpl
import com.example.testapp.domain.model.EntryViewData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class EntryRepositoryImplTest {

    private lateinit var entryDao: EntryDao
    private lateinit var repository: EntryRepositoryImpl

    @Before
    fun setup() {
        entryDao = mock(EntryDao::class.java)
        repository = EntryRepositoryImpl(entryDao)
    }

    @Test
    fun `addEntry should insert entry into database`() = runBlocking {
        // Given
        val entryViewData = EntryViewData(
            name = "Test Name",
            description = "This is a test description",
            wordCount = 5
        )

        // When
        repository.addEntry(entryViewData)

        // Then
        val expectedEntry = Entry(
            name = "Test Name",
            description = "This is a test description"
        )
        verify(entryDao).insert(expectedEntry)
    }

    @Test
    fun `getEntries should return a list of EntryViewData`() = runBlocking {
        // Given
        val entryEntities = listOf(
            Entry(name = "Test 1", description = "Description one"),
            Entry(name = "Test 2", description = "Description two")
        )
        `when`(entryDao.getAllEntries()).thenReturn(entryEntities)

        // When
        val result = repository.getEntries()

        // Then
        val expected = listOf(
            EntryViewData(name = "Test 1", description = "Description one", wordCount = 2),
            EntryViewData(name = "Test 2", description = "Description two", wordCount = 2)
        )
        assertEquals(expected, result)
    }
}
