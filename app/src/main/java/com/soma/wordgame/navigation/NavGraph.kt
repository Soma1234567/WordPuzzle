package com.soma.wordgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.soma.wordgame.presentation.all_levels.AllLevelScreen
import com.soma.wordgame.presentation.home_screen.HomeScreen
import com.soma.wordgame.presentation.level.Level

@Composable
fun NavGraph(navController:NavHostController){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(
            Screen.HomeScreen.route
        ){
            HomeScreen(navController)
        }
        composable(
            Screen.AllLevelScreen.route
        ){
            AllLevelScreen(navController)
        }
        composable(
            route = Screen.LevelScreen.route,
            arguments = listOf(
                navArgument("level"){
                    type = NavType.IntType
                }
            )
        ){
            val level = it.arguments?.getInt("level")!!
            Level(navController = navController, level = level)
        }
    }
}