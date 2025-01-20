package com.example.testapp.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testapp.domain.model.EntryViewData

@Composable
fun EntryItem(entry: EntryViewData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Name: ${entry.name}")
                Text(text = "Description: ${entry.description}")
            }
            Text(
                text = "Words: ${entry.wordCount}",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}