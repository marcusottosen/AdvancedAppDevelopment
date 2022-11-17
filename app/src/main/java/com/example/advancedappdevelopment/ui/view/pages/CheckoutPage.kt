package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.TempVehicle
import com.example.bkskjold.data.util.getTimeFromInt

@Composable
fun Checkout(vehicle: TempVehicle, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            //Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .padding(20.dp)
                .clickable { navController.navigate(NavigationRoute.Homepage.route) }

            ) {
                Icon(Icons.Filled.ArrowBack, "", tint = Color.DarkGray)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Checkout",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
                Image(
                    modifier = Modifier
                        .padding(20.dp)
                        .height(130.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.renault_zoe),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        item {
            Column(
                Modifier
                    .padding(start = 40.dp, top = 20.dp)
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Car: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Nr. ${vehicle.vehicle.carNum} - ${vehicle.vehicle.carName}",
                        fontSize = 15.sp
                    )
                }

                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Date: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = vehicle.chosenDate,
                        fontSize = 15.sp
                    )
                }

                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Hour(s): ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Column() {
                        for (hour in vehicle.chosenHours.listIterator()) {
                            Text(
                                text = getTimeFromInt(hour),
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                Row(modifier = Modifier.padding(top = 10.dp)) {
                    val price = vehicle.chosenHours.size * 30
                    Text(
                        text = "Price: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = price.toString(),
                        fontSize = 15.sp
                    )
                    Text(
                        text = " DKK",
                        fontSize = 15.sp
                    )
                }
                Text(
                    text = "30,- per hour",
                    //fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp
                )
            }
        }

    }

    var active by remember {
        mutableStateOf(false)
    }
    var sizeState by remember { mutableStateOf(0.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 40.dp, bottom = 80.dp)
    ){
        Button(
            onClick = {
                if (active)
                    sizeState = 0.dp
                else
                    sizeState = 400.dp

                active = !active
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(300.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary))
        ) {
            Text(text = "Checkout",
                color = Color.Black,
            modifier = Modifier.padding(7.dp))
        }
    }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        val interactionSource = MutableInteractionSource()  //Removes ripple-effect when transparent box is pressed

        Column() {
            Box(
                Modifier
                    .height(size)
                    .fillMaxWidth()
                    .clickable (
                        interactionSource = interactionSource,
                        indication = null
                            ) {
                        sizeState = 0.dp
                        active = false }
                    .background(Color.Transparent),
            )
            Box(
                modifier = Modifier
                    .height(size)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp, 27.dp))
                    .background(colorResource(id = R.color.dark_gray))
            ) {
                Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .padding(20.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(id = R.color.secondary))
                    ) {
                        Text(text = "MobilePay")
                    }

                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .padding(20.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(id = R.color.light_gray))
                    ) {
                        Text(text = "Credit Card")
                    }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 50.dp)
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(300.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary))
                        ) {
                            Text(
                                text = "Checkout",
                                color = Color.Black,
                                modifier = Modifier.padding(7.dp)
                            )
                        }
                    }
                }
            }
        }
    }
   /* AnimatedVisibility(
        visible = active,
        modifier = Modifier.fillMaxWidth(),
        enter = slideInVertically() + fadeIn()

    ) {
        //RoundedRect()
        AvailableVehicleCard(vehicle = vehicle.vehicle, navController = navController)
    }*/
}


@Composable
fun BuyPanel(size: Dp, sizeState: Dp){
    Column() {
        Box(
            Modifier
                .height(size)
                .fillMaxWidth()
                //.clickable { sizeState = 0.dp}
                .background(Color.Transparent)

        ) {

        }
        Box(
            modifier = Modifier
                .height(size)
                .fillMaxWidth()
                .clip(RoundedCornerShape(27.dp, 27.dp))
                .background(Color.Green)
        ) {
            Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .padding(20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                ) {
                    Text(text = "MobilePay")
                }

                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .padding(20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                ) {
                    Text(text = "Credit Card")
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 50.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(300.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                    ) {
                        Text(
                            text = "Checkout",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
