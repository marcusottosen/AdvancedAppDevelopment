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
import com.example.advancedappdevelopment.ui.viewmodel.CalendarViewModel
import com.example.advancedappdevelopment.ui.viewmodel.CarBookedFullDay
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
                    carBookedFullDay = vehicles.firstOrNull { it.date == dayState.date },
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
fun TimePickers(viewModel: CalendarViewModel){
    val hourList = listOf("01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
        "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
        "20:00", "21:00", "22:00", "23:00", "24:00")

    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        items(hourList.size) { index ->
            var chosenTime by remember { mutableStateOf(false)}

            Button(
                onClick = {chosenTime =! chosenTime},
                Modifier
                    .padding(5.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors =
                if (chosenTime)
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                else
                    ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.background)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = hourList[index],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp)
                )

            }
        }
    }
}




/**
 * Custom implementation of DayContent, which shows a dot if there is an recipe planned for this day.
 */
@Composable
fun BookedDay(
    state: DayState<DynamicSelectionState>,
    carBookedFullDay: CarBookedFullDay?,
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
            if (carBookedFullDay != null) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.secondary))
                )
                Text(
                    text = carBookedFullDay.text,
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

