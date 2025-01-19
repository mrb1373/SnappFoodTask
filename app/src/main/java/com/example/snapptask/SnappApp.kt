package com.example.snapptask

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.snapptask.navigation.SnappNavHost

@Composable
fun SnappApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    SnappNavHost(navController = navController, modifier = modifier)
}