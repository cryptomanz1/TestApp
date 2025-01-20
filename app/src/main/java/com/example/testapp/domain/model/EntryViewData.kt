package com.example.testapp.domain.model

/**
 * Represents a view model for an entry with additional computed properties.
 *
 * @property name The name of the entry.
 * @property description The description of the entry.
 * @property wordCount The number of words in the description.
 */
data class EntryViewData(
    val name: String,
    val description: String,
    val wordCount: Int,
)