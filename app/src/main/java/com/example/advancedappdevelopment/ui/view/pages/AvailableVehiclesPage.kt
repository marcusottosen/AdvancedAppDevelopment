package com.example.advancedappdevelopment.ui.view.pages

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.data.model.firebaseAdapter.associations
import com.example.advancedappdevelopment.ui.viewmodel.AvailableVehiclesViewModel
import kotlinx.coroutines.launch


@Composable
fun AvailableVehiclesPage(navController: NavController){
    val viewModel = AvailableVehiclesViewModel()
    val showAssociation = remember { mutableStateOf(0) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        item {
            PageTop(showAssociation)   //The drawer design at the top of the page
            Text(
                text = "Available vehicles",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            )
            Column() {
                viewModel.GetVehicleOverviewView(navController, associations[showAssociation.value].id)
            }
        }
    }
}

@Composable
fun PageTop(
    showAssociation: MutableState<Int>,
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f
){
    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.BottomCenter)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .background(colorResource(R.color.dark_gray))
            ){
                if (associations.size > 0) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center)
                    {
                        items(associations.size) { i ->         // Button with animation
                            Button(
                                onClick = {
                                    showAssociation.value = i;

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
                                },


                                if (showAssociation.value == i){
                                Modifier
                                    .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                                    .scale(scale = scale.value)}
                                else Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp),

                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    if (showAssociation.value == i) colorResource(R.color.primary)
                                    else colorResource(R.color.inactive)
                                )
                            ) {
                                Text(
                                    text = associations[i].name,
                                    fontWeight = FontWeight.Bold,
                                    color = if (showAssociation.value == i) colorResource(R.color.black)
                                    else colorResource(R.color.light_gray)
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 135.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp, 27.dp))
                    .background(colorResource(R.color.background))
            )
        }
    }
}

fun gotoVehicleDetails(vehicle: Vehicle, navController: NavController) {
    navController.currentBackStackEntry?.arguments?.putParcelable("vehicle", vehicle)
    navController.navigate(NavigationRoute.CarInfo.route)
}