package com.soma.wordgame.presentation.level

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soma.wordgame.navigation.Screen
import com.soma.wordgame.presentation.completedscreen.CompletedDialog
import com.soma.wordgame.util.allwords
import com.soma.wordgame.util.sizes
import com.soma.wordgame.util.uniqueCharactersInEachLevel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun Level(navController: NavHostController,level:Int,viewModel: LevelViewModel = getViewModel()){


    var foundedwords = remember {
        mutableStateMapOf<String,String>()
    }

    val context = LocalContext.current
    var size = sizes.get(level)
    var wordsdata = remember {
        mutableStateListOf<Char>().also {words->
            repeat(size.first*size.second){
                words.add(' ')
            }
        }
    }
    var coroutinescope = rememberCoroutineScope()
    var words = allwords.get(level)
    if(foundedwords.size==words.size){
        viewModel.savelevel(level)
        CompletedDialog(ondismiss = { }, onnextlevel = {
            navController.popBackStack(route = Screen.HomeScreen.route,false)
            navController.navigate(Screen.LevelScreen.withlevel(level+1))
        }) {
            navController.popBackStack(route = Screen.HomeScreen.route,false)
            navController.navigate(Screen.HomeScreen.route)
        }
    }
    var charactersAt = mutableListOf<Int>().also {
        for (word in words.keys){
            it.addAll(words[word]!!)
        }
    }
    var characters = uniqueCharactersInEachLevel.get(level)
    Toast.makeText(context,characters.toString(),Toast.LENGTH_SHORT).show()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color(0xFF6290C3))
                        .padding(start = 20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Level ${level+1}", color = Color.White, fontSize = 25.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (i in 0 until size.first){
                        Row(
                            modifier = Modifier.padding(vertical = 3.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            for (j in 0 until size.second){
                                CharacterBox(character = wordsdata[i*size.second+j], index = i*size.second+j, charactersAt =charactersAt )
                            }
                        }
                    }
                }

            }
        Circle(chars = characters, modifier = Modifier
            .padding(bottom = 20.dp)
            .align(Alignment.BottomCenter),
            ondragcomplete = {result->
                if(result in words.keys){
                    foundedwords.put(result,"")
                    var positions = words[result]!!
                    coroutinescope.launch {
                        for (i in 0 until result.length){
                            wordsdata[positions[i]] = result[i]
                            delay(100)
                        }
                    }

                }
            })

    }



}

@Composable
fun CharacterBox(character:Char,index:Int,charactersAt:List<Int>){
    Box(
        modifier = Modifier
            .size(50.dp)
            .then(
                if (index in charactersAt) {
                    if (character == ' ') {
                        Modifier.background(Color(0xFFffd1dc), shape = RoundedCornerShape(8.dp))
                    } else {
                        Modifier.background(Color(0xFFAD7149), shape = RoundedCornerShape(8.dp))
                    }
                } else {
                    Modifier.background(Color.Transparent)
                }
            ),
        contentAlignment = Alignment.Center
    ){
        if(index in charactersAt){
            Text(text = character.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold, color = if(character!=' ') Color.White else Color.Black)
        }
    }
}

@Composable
fun Circle(chars:List<Char>,modifier: Modifier,ondragcomplete:(String)->Unit){
    val textMeasurer1 = rememberTextMeasurer()
    val centers  = mutableListOf<Offset>()
    val context = LocalContext.current
    var selectedrows = remember {
       mutableStateListOf<Int>()
    }
    var startingline = remember {
        mutableStateOf(Offset(0f,0f))
    }
    var currentlinePosition = remember {
        mutableStateOf<Offset>(startingline.value)
    }
    var lines = remember {
        mutableStateListOf<Offset>()
    }
    var result = remember {
        mutableStateOf("")
    }
    Box(
        modifier = modifier
            .size(250.dp)
            .clip(RoundedCornerShape(125.dp))
            .background(Color.White)
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { start ->
                        for (i in 0 until chars.size) {
                            val position = centers[i]
                            if ((start.x >= (position.x - dpToPx(value = 25.dp)) && start.x <= (position.x + dpToPx(
                                    value = 25.dp
                                )))
                                && (start.y >= (position.y - dpToPx(value = 25.dp)) && start.y <= (position.y + dpToPx(
                                    value = 25.dp
                                )))
                            ) {
                                selectedrows.add(i)
                                startingline.value = position
                                lines.add(startingline.value)
                                result.value = result.value + chars[i]
                                break
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        for (i in 0 until chars.size) {
                            val position = centers[i]
                            if ((change.position.x >= (position.x - dpToPx(value = 25.dp)) && change.position.x <= (position.x + dpToPx(
                                    value = 25.dp
                                )))
                                && (change.position.y >= (position.y - dpToPx(value = 25.dp)) && change.position.y <= (position.y + dpToPx(
                                    value = 25.dp
                                )))
                            ) {
                                if (!(position in lines)) {
                                    selectedrows.add(i)
                                    startingline.value = position
                                    result.value = result.value + chars[i]
                                    lines.add(position)

                                }
                                break
                            }
                        }
                        currentlinePosition.value = change.position
                    },
                    onDragEnd = {
                        selectedrows.clear()
                        startingline.value = Offset(0f, 0f)
                        currentlinePosition.value = Offset(0f, 0f)
                        lines.clear()
                        val res = result.value
                        result.value = ""
                        ondragcomplete(res)
                    },
                    onDragCancel = {
                        selectedrows.clear()
                        startingline.value = Offset(0f, 0f)
                        currentlinePosition.value = Offset(0f, 0f)
                        lines.clear()
                        val res = result.value
                        result.value = ""
                        ondragcomplete(res)
                    }
                )

            }
            .drawBehind {
                if (lines.size >= 2) {
                    drawlines(lines)
                }
                if (lines.size >= 1) {
                    drawLine(
                        color = Color(0xFFAD7149),
                        start = lines[lines.size - 1],
                        end = currentlinePosition.value,
                        strokeWidth = 15f
                    )
                }

                centers.add(Offset(125.dp.toPx(), 40.dp.toPx()))
                centers.add(Offset(45.dp.toPx(), 100.dp.toPx()))
                centers.add(Offset(205.dp.toPx(), 100.dp.toPx()))
                centers.add(Offset(65.dp.toPx(), 180.dp.toPx()))
                centers.add(Offset(185.dp.toPx(), 180.dp.toPx()))
                for (i in 0 until chars.size) {
                    TextCircle(centre1 = centers[i], textMeasurer1, chars[i], i in selectedrows)

                }
//                TextCircle(centre1 = Offset(45.dp.toPx(), 100.dp.toPx()),textMeasurer,'B')
//                TextCircle(centre1 = Offset(205.dp.toPx(), 100.dp.toPx()),textMeasurer,'C')
//                TextCircle(centre1 = Offset(65.dp.toPx(), 180.dp.toPx()),textMeasurer,'D')
//                TextCircle(centre1 = Offset(185.dp.toPx(), 180.dp.toPx()),textMeasurer,'E')
            }
    ){
    }
}

@Composable
fun TextInput(modifier: Modifier,letter:Char){
    Box(modifier = modifier
        .size(50.dp)
        .background(Color(0xFFAD7149), shape = RoundedCornerShape(50.dp)))
}

fun DrawScope.TextCircle(centre1:Offset,textMeasurer: TextMeasurer,character: Char,isselcted:Boolean){
        drawCircle(
            color = if(isselcted) Color(0xFFAD7149) else Color.White,
            center =centre1 ,
            style = Fill,
            radius = 25.dp.toPx()
        )
       drawText(
           textMeasurer = textMeasurer,
           text = character.toString(),
           topLeft = Offset(centre1.x-10.dp.toPx(),centre1.y-22.dp.toPx()),
           style = TextStyle(
               color = Color.Black,
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold,
           )
       )
}
fun PointerInputScope.dpToPx(value: Dp):Float{
    return with(density){
        value.toPx()
    }
}

fun PointerInputScope.pxToDp(value:Float):Dp{
    return with(density){
        value.toDp()
    }
}

fun DrawScope.drawlines(lines:List<Offset>){
    for(i in 0 until lines.size-1){
        drawLine(
            color = Color(0xFFAD7149),
            start = lines[i],
            end = lines[i+1],
            strokeWidth = 15f
        )
    }
}
