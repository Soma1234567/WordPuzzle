package com.soma.wordgame.navigation

sealed class Screen(val route:String){
    object HomeScreen:Screen("home_screen")
    object LevelScreen:Screen("level_screen/{level}"){
        fun withlevel(level:Int):String{
            return "level_screen/$level"
        }
    }

    object AllLevelScreen:Screen("all_levels")
}