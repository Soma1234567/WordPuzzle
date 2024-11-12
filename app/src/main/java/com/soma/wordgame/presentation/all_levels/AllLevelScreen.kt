package com.soma.wordgame.presentation.all_levels

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.wordgame.R
import com.soma.wordgame.navigation.Screen
import com.soma.wordgame.util.allwords
import org.koin.androidx.compose.getViewModel
import java.util.logging.Level

@Composable
fun AllLevelScreen(navController: NavHostController,viewModel: AllLevelViewModel = getViewModel()){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFF6290C3))
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "All Levels", color = Color.White, fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val total_levels = allwords.size
            var completedlevels = viewModel.level.collectAsState()
            for(i in 0 until total_levels){
                if(i<=completedlevels.value+1){
                    CompletedLevelBoard(level = i+1) {
                        navController.navigate(Screen.LevelScreen.withlevel(i))
                    }
                }
                else{
                    NotCompletedLevel(level = i+1) {
                        Toast.makeText(context,"Please complete above levels",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}

@Composable
fun CompletedLevelBoard(level: Int,onclick:()->Unit){
    Box(
       modifier = Modifier
           .padding(horizontal = 20.dp, vertical = 5.dp)
           .fillMaxWidth()
           .height(100.dp)

           .background(
               color = Color.White,
               shape = RoundedCornerShape(15.dp)
           )
           .padding(end = 20.dp)
           .clickable {
               onclick()
           }
    ){
        Text(text = "Play Level $level", fontSize = 22.sp, color = Color.Black, modifier = Modifier.align(
            Alignment.Center), fontFamily = FontFamily(Font(R.font.levelfont))
        )
        Image(painter = painterResource(id = R.drawable.unlocked), contentDescription = null, modifier = Modifier
            .size(32.dp)
            .align(Alignment.CenterEnd))


    }
}

@Composable
fun NotCompletedLevel(level: Int,onclick: () -> Unit){
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            .padding(end = 20.dp)
            .clickable {
                onclick()
            }
            )
    {
        Text(text = "Level $level", fontSize = 22.sp, modifier = Modifier.align(Alignment.Center), fontFamily = FontFamily(
            Font(R.font.levelfont)
        ))
        Image(painter = painterResource(id = R.drawable.lock), contentDescription = null, modifier = Modifier
            .size(32.dp)
            .align(Alignment.CenterEnd))
    }
}