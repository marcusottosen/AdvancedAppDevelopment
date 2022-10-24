package com.example.advancedappdevelopment.ui.view.pages

import android.widget.CalendarView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.util.*

@Composable
fun CarInfo(vehicle: Vehicle, navController: NavController){
    val calendarState = rememberSelectableCalendarState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Book Now", color = Color.Black) },
                onClick = { navController.navigate(NavigationRoute.Checkout.route) },
                icon = { Icon(Icons.Filled.ArrowForward, "", tint = Color.Black)},
                modifier = Modifier.padding(bottom = 60.dp),
                backgroundColor = colorResource(id = R.color.primary)
            )
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()
            ){
                item {
                    CarInfoPageTop(navController)
                }
                item {
                    Text(
                        text = "Car ${vehicle.carNum} - ${vehicle.carName}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(30.dp, 0.dp, 10.dp),
                        fontSize = 20.sp
                    )

                    Card(   // Header picture of vehicle
                        modifier = Modifier
                            .padding(30.dp, 10.dp, 30.dp)
                            .height(150.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = colorResource(R.color.light_gray),
                    ){
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
                    ){
                        Row(
                            Modifier
                                .padding(20.dp)
                                .fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                                Box(modifier = boxModifier
                                ) {
                                    Text(text = "Plate: ${vehicle.plate}"
                                    )
                                }
                                Box(modifier = boxModifier
                                ) {
                                    Text(text = "Range: ${vehicle.range}km"
                                    )
                                }
                            }
                            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                                Box(modifier = boxModifier
                                ) {
                                    Text(text = "Seats: ${vehicle.seats}"
                                    )
                                }
                                Box(modifier = boxModifier
                                ) {
                                    Text(text = "Boot space: ${vehicle.bootSpace}"
                                    )
                                }
                            }
                        }
                    }
                    /*
                    Box(modifier = Modifier
                        .padding(30.dp, 0.dp, 30.dp, 20.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(colorResource(R.color.light_gray))) {
                        Row(
                            modifier = Modifier
                                .padding(6.dp),
                            verticalAlignment = Alignment.Top
                        ) {

                            AndroidView(
                                { CalendarView(it) },
                                modifier = Modifier.wrapContentWidth(),
                                update = { views ->
                                    views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                                        val cal = Calendar.getInstance()
                                        cal.set(year, month, dayOfMonth)
                                    }
                                }
                            )

                            Text(
                                text = "Today",
                                modifier = Modifier
                                    .wrapContentWidth(),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color(0xFF0A70C4),
                                textAlign = TextAlign.Center,
                            )

                        }
                    }*/

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    // https://github.com/boguszpawlowski/ComposeCalendar
                    Box(modifier = Modifier
                        .padding(30.dp, 0.dp, 30.dp, 20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.light_gray)))
                    {

                        Column(
                            Modifier.padding(20.dp)
                        ) {
                            SelectableCalendar(calendarState = calendarState)

                            SelectionControls(selectionState = calendarState.selectionState)
                        }

                    }
                    Spacer(modifier = Modifier.padding(100.dp))
                }
            }
        }
    )
}

@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
    Text(
        text = "Calendar Selection Mode",
        style = MaterialTheme.typography.h5,
    )
    SelectionMode.values().forEach { selectionMode ->
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = selectionState.selectionMode == selectionMode,
                onClick = { selectionState.selectionMode = selectionMode }
            )
            Text(text = selectionMode.name)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

    Text(
        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}",
        style = MaterialTheme.typography.h6,
    )
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