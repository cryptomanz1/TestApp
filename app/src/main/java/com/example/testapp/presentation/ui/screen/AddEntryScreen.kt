package com.example.testapp.presentation.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapp.presentation.viewmodel.EntryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEntryScreen(navController: NavController, viewModel: EntryViewModel = koinViewModel()) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val focusRequesterName = remember { FocusRequester() }
    val focusRequesterDescription = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FormContent(
                name = name,
                onNameChange = { name = it },
                description = description,
                onDescriptionChange = { description = it },
                focusRequesterName = focusRequesterName,
                focusRequesterDescription = focusRequesterDescription,
                keyboardController = keyboardController,
                focusManager = focusManager,
                onAddClick = {
                    if (name.isNotBlank() && description.isNotBlank()) {
                        viewModel.addEntry(name, description)
                        navController.popBackStack()
                    }
                },
                onListClick = { navController.navigate("entryListScreen") },
                modifier = Modifier
                    .weight(1f)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            FormContent(
                name = name,
                onNameChange = { name = it },
                description = description,
                onDescriptionChange = { description = it },
                focusRequesterName = focusRequesterName,
                focusRequesterDescription = focusRequesterDescription,
                keyboardController = keyboardController,
                focusManager = focusManager,
                onAddClick = {
                    if (name.isNotBlank() && description.isNotBlank()) {
                        viewModel.addEntry(name, description)
                        navController.popBackStack()
                    }
                },
                onListClick = { navController.navigate("entryListScreen") }
            )
        }
    }
}

@Composable
fun FormContent(
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    focusRequesterName: FocusRequester,
    focusRequesterDescription: FocusRequester,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    onAddClick: () -> Unit,
    onListClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Entry Form", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier
            .height(16.dp)
        )

        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterName),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterDescription.requestFocus() }
            )
        )

        Spacer(modifier = Modifier
                .height(8.dp)
        )

        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterDescription),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        )

        Spacer(modifier = Modifier
                .height(16.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Add Entry")
        }

        Spacer(modifier = Modifier
                .height(16.dp)
        )

        Button(
            onClick = onListClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Go to List")
        }
    }
}
