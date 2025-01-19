package com.example.snapptask.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.snapp.character.CharacterScreen
import com.snapp.feature.home.HomeScreen


@SuppressLint("NewApi")
@Composable
fun SnappNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen {
                item ->
                navController.navigate(route = Character(item.url))
            }
        }

        composable<Character> { navBackStackEntry ->
            val char: Character = navBackStackEntry.toRoute()
            CharacterScreen(character = char.url)
        }
    }
}