package com.soma.wordgame.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.soma.wordgame.R
import com.soma.wordgame.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = getViewModel()){
    val level by viewModel.level.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
    )
    {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "WordWave", fontFamily = FontFamily(
                Font(R.font.levelfont)
            ),
                fontSize = 28.sp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Ride the Wave of Words!", fontFamily = FontFamily(
                Font(R.font.levelfont)
            ),
                fontSize = 20.sp, color = Color.White)

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
                    .background(color = Color(0xFF6290C3), shape = RoundedCornerShape(10.dp))
                    .clickable {
                               navController.navigate(Screen.LevelScreen.withlevel(level+1))
                    },
                contentAlignment = Alignment.Center
            ){
                Text(text = "Play", fontSize = 20.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
                    .background(color = Color(0xFF6290C3), shape = RoundedCornerShape(10.dp))
                    .clickable {
                               navController.navigate(Screen.AllLevelScreen.route)
                    },
                contentAlignment = Alignment.Center
            ){
                Text(text = "Levels", fontSize = 20.sp, color = Color.White)
            }
            }

    }
}