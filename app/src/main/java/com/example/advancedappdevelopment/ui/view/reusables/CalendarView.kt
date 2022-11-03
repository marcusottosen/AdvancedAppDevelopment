package com.example.advancedappdevelopment.ui.view.reusables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
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

// https://github.com/boguszpawlowski/ComposeCalendar

@Composable
fun MyCalendarView(){
    val viewModel = remember { CalendarViewModel() }
    val vehicles by viewModel.vehicleFlow.collectAsState()
    val selectedPrice by viewModel.selectedRecipesPriceFlow.collectAsState(0)
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
        Text(
            text = "Selected recipes price: $selectedPrice",
            style = MaterialTheme.typography.h6,
        )

        Text(
            text = "Date chosen: $chosenDate",
            style = MaterialTheme.typography.h6,
        )

        Spacer(modifier = Modifier.height(20.dp))
        //SelectionControls(selectionState = calendarState.selectionState)
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
            .padding(2.dp),
        elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
        border = if (state.isCurrentDay) BorderStroke(1.dp, MaterialTheme.colors.primary) else null,
        contentColor = if (isSelected) colorResource(R.color.secondary) else contentColorFor(
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
                        .background(MaterialTheme.colors.primary)
                )
                Text(
                    text = carBookedFullDay.price.toString(),
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

