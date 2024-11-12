package com.soma.wordgame.presentation.completedscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.soma.wordgame.R

@Composable
fun CompletedDialog(ondismiss:()->Unit,onnextlevel:()->Unit,onhome:()->Unit){

    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(

        LottieCompositionSpec
            .RawRes(R.raw.animation)
    )

    val progress by animateLottieCompositionAsState(
        composition,

        iterations = LottieConstants.IterateForever,

        isPlaying = isPlaying,

        speed = speed,

        restartOnPlay = true

    )

    Dialog(onDismissRequest = { ondismiss()}) {

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color(0xFF6290C3), shape = RoundedCornerShape(15.dp))
                        .clickable {
                                   onnextlevel()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Next Level", fontSize = 20.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color(0xFF6290C3), shape = RoundedCornerShape(15.dp))
                        .clickable {
                                   onhome()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Go Home", fontSize = 20.sp, color = Color.White)
                }
            }


        }
    }
}