package com.example.testapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testapp.presentation.ui.screen.AddEntryScreen
import com.example.testapp.presentation.ui.screen.EntryListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "addEntryScreen") {
        composable("entryListScreen") { EntryListScreen(navController) }
        composable("addEntryScreen") { AddEntryScreen(navController) }
    }
}