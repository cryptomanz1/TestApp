package com.example.testapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.model.EntryViewData
import com.example.testapp.domain.repository.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(private val entryRepository: EntryRepository) : ViewModel() {

    private val _entries = mutableStateListOf<EntryViewData>()
    val entries: List<EntryViewData> get() = _entries

    fun loadEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            _entries.clear()
            _entries.addAll(entryRepository.getEntries())
        }
    }

    fun addEntry(name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entry = EntryViewData(
                name = name,
                description = description,
                wordCount = 0 // We are not storing count here
            )
            entryRepository.addEntry(entry)
            loadEntries()
        }
    }
}
