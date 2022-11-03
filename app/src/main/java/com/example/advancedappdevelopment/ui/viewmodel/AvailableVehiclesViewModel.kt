package com.example.advancedappdevelopment.ui.viewmodel

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.firebaseAdapter.vehicles
import com.example.advancedappdevelopment.ui.view.reusables.AvailableVehicleCard
import kotlinx.coroutines.launch

class AvailableVehiclesViewModel {

    @Composable
    fun GetAssociations(){
    }

	@Composable
    fun GetVehicleOverviewView(
		navController: NavController,
		showAssociation: String
	){
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


		if (vehicles.size > 0) {
			for (i in 0 until vehicles.size) {
				if (vehicles[i].association == showAssociation) {
					AvailableVehicleCard(
						vehicle = vehicles[i],
						navController = navController,
						//scale = scale
					)
				}
			}
		}
    }

}

/*
                coroutineScope.launch {
                    scale.animateTo(
                        scaleDown,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(animationDuration),
                    )
                }
 */


