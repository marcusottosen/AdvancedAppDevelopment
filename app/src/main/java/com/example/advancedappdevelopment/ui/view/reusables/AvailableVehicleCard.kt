package com.example.advancedappdevelopment.ui.view.reusables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.ui.view.pages.gotoVehicleDetails


@Composable
fun AvailableVehicleCard(
    vehicle: Vehicle,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .padding(30.dp, 10.dp, 30.dp, 20.dp)
            .height(85.dp)
            .fillMaxWidth()
            .clickable {
                gotoVehicleDetails(vehicle, navController)
            },
        shape = RoundedCornerShape(9.dp),
        backgroundColor = colorResource(R.color.background),
        elevation = 6.dp,

    ) {
        Row {
            Column(modifier = Modifier.width(95.dp)) {
                Text(
                    text = "Car ${vehicle.carNum}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 20.dp, 10.dp),
                    fontSize = 16.sp
                )
                Text(
                    text = vehicle.carName,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(10.dp, 5.dp, 5.dp),
                    color = Color.DarkGray
                )
            }

            Column {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.padding(20.dp, 15.dp, 0.dp, 0.dp)
                ) {
                    Column { //TODO Add battery icon
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Text(
                                text = "${vehicle.battery}%",
                                modifier = Modifier.padding(start = 5.dp),
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                        Text(
                            text = "Next availability",
                            modifier = Modifier.padding(start = 5.dp),
                            fontSize = 11.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "10:30 - today",
                            modifier = Modifier.padding(start = 5.dp),
                            fontSize = 11.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier =  Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.renault_zoe),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 210.dp)
                    .height(85.dp)
            )
        }
    }
}
