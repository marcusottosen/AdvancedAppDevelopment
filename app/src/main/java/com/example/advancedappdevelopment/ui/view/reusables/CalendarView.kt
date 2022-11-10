package com.example.advancedappdevelopment.ui.view.reusables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.ui.viewmodel.CalendarViewModel
import com.example.advancedappdevelopment.ui.viewmodel.CarBookedDay
import com.example.bkskjold.data.util.getHour
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode

/**
 * Calendar item from https://github.com/boguszpawlowski/ComposeCalendar
 */
@Composable
fun MyCalendarView(viewModel: CalendarViewModel){
    //val viewModel = remember { CalendarViewModel() }
    val vehicles by viewModel.vehicleFlow.collectAsState()
    //val selectedPrice by viewModel.selectedRecipesPriceFlow.collectAsState(0)
    val chosenDate by remember {viewModel.chosenDate}


    val state = rememberSelectableCalendarState(
        confirmSelectionChange = { viewModel.onSelectionChanged(it); true },
        initialSelectionMode = SelectionMode.Single,
    )

    Column {
        SelectableCalendar(
            calendarState = state,
            dayContent = { dayState ->
                BookedDay(
                    state = dayState,
                    carBookedDay = vehicles.firstOrNull { it.date == dayState.date },
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        /*Text(
            text = "Selected recipes price: $selectedPrice",
            style = MaterialTheme.typography.h6,
        )*/

        Text(
            text = "Date chosen: $chosenDate",
            style = MaterialTheme.typography.h6,
        )

        Spacer(modifier = Modifier.height(20.dp))
        //SelectionControls(selectionState = calendarState.selectionState)
    }
}

@Composable
fun TimePicker(){   //Same as TimerPickers but instead made with rows and columns
    var time = 1

    Row(modifier = Modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
        for (b in 0..3) {
            Column(modifier = Modifier.fillMaxHeight(), Arrangement.SpaceEvenly) {
                for (b in 0..5) {
                    var chosenTime by remember { mutableStateOf(false)}

                    Button(
                        onClick = { chosenTime =! chosenTime },
                        Modifier
                            .padding(0.dp, 0.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors =
                        if (chosenTime)
                            ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                        else
                            ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.background))

                    ) {
                        Text(text = "$time:00",
                            textAlign = TextAlign.Center)
                        time++

                    }
                    /*Box(
                        Modifier
                            .padding(5.dp, 10.dp)
                            .size(60.dp, 25.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                1.dp,
                                colorResource(R.color.dark_gray),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(colorResource(R.color.background)),
                    ) {
                        Text(text = "$time:00",
                            textAlign = TextAlign.Center)
                        time++
                    }*/
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimePickers(viewModel: CalendarViewModel, vehicle: Vehicle){
    // List of all booked dates
    val bookedDatesAsString = mutableListOf<String>()
    for (i in 0 until viewModel.bookedDates.size){
        bookedDatesAsString.add(viewModel.bookedDates[i].date.toString())
    }

    val bookingHourStarts = mutableListOf<Int>()
    for (i in 0 until vehicle.bookingStart.size){
        bookingHourStarts.add(getHour(vehicle.bookingStart[i]))
    }


    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        items(viewModel.hourList.size) { index ->
            var chosenTime by remember { mutableStateOf(false)}

            var isOnDate = false
            if (bookedDatesAsString.contains(viewModel.chosenDate.value)){
                isOnDate = true
            }
            var hourIsBooked = false

            for (hourlist in viewModel.bookedHoursPerDay){          // For each booking
                if (hourlist[0] == viewModel.chosenDate.value){     // and if the date of the booking matches the chosen date
                    for (i in 1 until hourlist.size){        // then for each booked hour on the date
                        if (hourlist[i] == index)                   // if the hour equals the button index
                            hourIsBooked = true                     // true that the hour is booked
                    }
                }
            }

            if (hourIsBooked)
                chosenTime = false

            Button(
                onClick = {
                    if (!hourIsBooked)
                        chosenTime =! chosenTime},
                Modifier
                    .padding(5.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp)),   // && bookingHourStarts.contains(index)
                colors =    //viewModel.bookedDates[2].date.toString() == viewModel.chosenDate.value && getHour(vehicle.bookingStart[2]) == index
                //if (bookedDatesAsString.contains(viewModel.chosenDate.value) && viewModel.bookedHoursPerDay[0].contains(0)) {
                if (hourIsBooked) {
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.secondary))
                }
                else if (chosenTime)    // if hour is pressed. change color to primary
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                else
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.background)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = viewModel.hourList[index],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}

/**
 * Custom implementation of DayContent, which shows a dot if there is a booking that day.
 */
@Composable
fun BookedDay(
    state: DayState<DynamicSelectionState>,
    carBookedDay: CarBookedDay?,
    modifier: Modifier = Modifier,
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            //.clip(RoundedCornerShape(27.dp))
            .padding(2.dp),
        elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
        border = if (state.isCurrentDay) BorderStroke(1.dp, MaterialTheme.colors.primary) else null,
        contentColor = if (isSelected) colorResource(R.color.primary) else contentColorFor(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Column(
            modifier = Modifier.clickable {
                selectionState.onDateSelected(date)
            },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = date.dayOfMonth.toString())
            if (carBookedDay != null) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.secondary))
                )
                Text(
                    text = carBookedDay.text,
                    fontSize = 8.sp,
                )
            }
        }
    }
}
/*
@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
    Text(
        text = "Calendar Selection Mode",
        style = MaterialTheme.typography.h5,
    )
/*
    SelectionMode.values().forEach { selectionMode ->
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = selectionState.selectionMode == selectionMode,
                onClick = { selectionState.selectionMode = selectionMode }
            )
            Text(text = selectionMode.name)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }*/

    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(
            selected = selectionState.selectionMode == SelectionMode.Single,
            onClick = { selectionState.selectionMode = SelectionMode.Single }
        )
        Text(text = SelectionMode.Single.name)
        Spacer(modifier = Modifier.height(4.dp))
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(
            selected = selectionState.selectionMode == SelectionMode.Period,
            onClick = { selectionState.selectionMode = SelectionMode.Period }
        )
        Text(text = SelectionMode.Period.name)
        Spacer(modifier = Modifier.height(4.dp))
    }

    Text(
        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}",
        style = MaterialTheme.typography.h6,
    )
}

*/

