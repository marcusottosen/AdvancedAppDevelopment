package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.ui.view.reusables.MyCalendarView
import com.example.advancedappdevelopment.ui.view.reusables.TimePicker
import com.example.advancedappdevelopment.ui.viewmodel.CalendarViewModel
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@Composable
fun CarInfo(vehicle: Vehicle, navController: NavController){
    val calendarState = rememberSelectableCalendarState()
    val viewModel = remember { CalendarViewModel(vehicle) }
    val chosenDate by remember {viewModel.chosenDate}

    LazyColumn(modifier = Modifier.fillMaxSize()
    ){
        item {
            CarInfoPageTop(navController)
        }

        item {          // Vehicle name and picture
            /*println("car info:")
			println(vehicle.bookingStart.toString())
			println(getDay(vehicle.bookingStart[0]))
			println(vehicle.bookingEnd.toString())
			println("car info as kotlin date")
			val startDate = vehicle.bookingStart[0].toDate()
			println(startDate)
			println(startDate.time)
			println(startDate.date)
			println(startDate.hours)
			 */

            Text(
                text = "Car ${vehicle.carNum} - ${vehicle.carName}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(30.dp, 0.dp, 10.dp),
                fontSize = 20.sp
            )

            Card(
                // Header picture of vehicle
                modifier = Modifier
                    .padding(30.dp, 10.dp, 30.dp)
                    .height(150.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = colorResource(R.color.light_gray),
            ) {
                Image(  //TODO Make rounder corners work?
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = R.drawable.renault_zoe),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        item {          // Vehicle info

            Spacer(modifier = Modifier.padding(top = 20.dp))

            val boxModifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(R.color.background))
                .padding(10.dp)

            Box( // Vehicle details
                modifier = Modifier
                    .padding(30.dp, 0.dp, 30.dp)
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.light_gray))
            ) {
                Row(
                    Modifier
                        .padding(20.dp)
                        .fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = boxModifier
                        ) {
                            Text(
                                text = "Plate: ${vehicle.plate}"
                            )
                        }
                        Box(
                            modifier = boxModifier
                        ) {
                            Text(
                                text = "Range: ${vehicle.range}km"
                            )
                        }
                    }
                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = boxModifier
                        ) {
                            Text(
                                text = "Seats: ${vehicle.seats}"
                            )
                        }
                        Box(
                            modifier = boxModifier
                        ) {
                            Text(
                                text = "Boot space: ${vehicle.bootSpace}"
                            )
                        }
                    }
                }
            }
        }

        item {          // Calendar
            Spacer(modifier = Modifier.padding(top = 20.dp))

            /**
             * Calendar Item from https://github.com/boguszpawlowski/ComposeCalendar
             * And customized in CalendarView.kt
             */
            Box(
                modifier = Modifier
                    .padding(30.dp, 0.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.light_gray))
            )
            {

                Column(
                    Modifier.padding(20.dp)
                ) {
                    Text(text = "*Choose the day for the reservation*")
                    MyCalendarView(viewModel)
                }
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))
        }

        item {          // 24-hour time picker
            Box(modifier = Modifier
                .padding(30.dp, 0.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(colorResource(R.color.light_gray)))
            {
                Column(
                    Modifier.padding(20.dp)
                ) {
                    /*Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
						Text(text = "Night", fontWeight= FontWeight.SemiBold)
						Text(text = "Morning", fontWeight= FontWeight.SemiBold)
						Text(text = "Afternoon", fontWeight= FontWeight.SemiBold)
						Text(text = "Evening", fontWeight= FontWeight.SemiBold)
					}*/
                    Text(text = "*Choose the hours for the reservation*")
                    TimePicker(viewModel, vehicle)

                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 30.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                    ) {


                Button(
                    onClick = { gotoCheckoutInfo(vehicle, navController) },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary)),
                    enabled = chosenDate.isNotEmpty() && viewModel.chosenHours.size > 0
                ) {
                    Text(
                        text = "Book vehicle",
                        fontWeight = FontWeight.Bold
                    )
                }
           /* }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 30.dp),
                horizontalArrangement = Arrangement.End,
            ) {*/
                    Text(text =
                    if(chosenDate.isEmpty() && viewModel.chosenHours.isEmpty())
                        "Please pick one or more hours \n" +
                                " Please pick a date"
                    else if (viewModel.chosenHours.size < 1)
                        "Please pick one or more hours \n "
                    else if (chosenDate.isEmpty())
                        "Please pick a date \n "
                    else
                        " \n ",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 60.dp))
        }
    }
}


@Composable
fun CarInfoPageTop(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.BottomCenter)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(colorResource(R.color.dark_gray))
            ){
                Box(modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.navigate(NavigationRoute.Homepage.route) }

                ) {
                    Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 75.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp, 27.dp))
                    .background(colorResource(R.color.background))
            )
        }
    }
}

fun gotoCheckoutInfo(vehicle: Vehicle, navController: NavController) {
    navController.currentBackStackEntry?.arguments?.putParcelable("vehicle", vehicle)
    navController.navigate(NavigationRoute.Checkout.route)
}