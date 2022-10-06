package com.example.advancedappdevelopment.ui.viewmodel
/*
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import com.example.advancedappdevelopment.R
*/
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*

import com.example.advancedappdevelopment.ui.view.reusables.AvailableVehicleCard

class AvailableVehiclesViewModel {

    //@OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun GetOverviewView(){
        /*Column {
            var showRed by remember { mutableStateOf(true) }
            var showGreen by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = showGreen,
                // By Default, `scaleIn` uses the center as its pivot point. When used with a vertical
                // expansion from the vertical center, the content will be growing from the center of
                // the vertically expanding layout.
                enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
                // By Default, `scaleOut` uses the center as its pivot point. When used with an
                // ExitTransition that shrinks towards the center, the content will be shrinking both
                // in terms of scale and layout size towards the center.
                exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
            ) {
                Box(
                    Modifier.size(100.dp)
                        .background(color = Color.Green, shape = RoundedCornerShape(20.dp))
                )
            }

            AnimatedVisibility(
                visible = showRed,
                // Scale up from the TopLeft by setting TransformOrigin to (0f, 0f), while expanding the
                // layout size from Top start and fading. This will create a coherent look as if the
                // scale is impacting the size.
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn() + expandIn(expandFrom = Alignment.TopStart),
                // Scale down from the TopLeft by setting TransformOrigin to (0f, 0f), while shrinking
                // the layout towards Top start and fading. This will create a coherent look as if the
                // scale is impacting the layout size.
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box(
                    Modifier.size(100.dp)
                        .background(color = Color.Red, shape = RoundedCornerShape(20.dp))
                )
            }
        }*/
        Column(){
            for (i in 0..5){
                AvailableVehicleCard()
            }
        }
    }
}


