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
    val vehicles by viewModel.vehicleFlow.collectAsState()
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
        Text(
            text = "Date chosen: $chosenDate",
            style = MaterialTheme.typography.body1,
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

/**
 * Shows 24-hour time picker.
 * Each hour can be clicked on making it green.
 * Each hour already booked is red.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimePicker(viewModel: CalendarViewModel, vehicle: Vehicle){
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
        items(viewModel.hourList.size) { index ->   // The following is for EACH hour-button
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

            // If the hour is already booked that day, don't show it as chosen.
            if (hourIsBooked)
                chosenTime = false

            Button(
                onClick = {
                    if (!hourIsBooked)
                        chosenTime =! chosenTime},
                Modifier
                    .padding(5.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors =
                if (hourIsBooked) {     // If hour is booked change color to red.
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.secondary))
                }
                else if (chosenTime)    // if hour is pressed. change color to primary.
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