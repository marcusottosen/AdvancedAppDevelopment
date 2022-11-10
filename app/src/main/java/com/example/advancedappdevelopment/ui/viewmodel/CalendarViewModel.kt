package com.example.advancedappdevelopment.ui.viewmodel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getMonthNum
import com.example.bkskjold.data.util.getYear
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

data class CarBookedFullDay(
    val date: LocalDate,
    val text: String,
)

/**
 * Calendar item from https://github.com/boguszpawlowski/ComposeCalendar
 */
class CalendarViewModel(vehicle: Vehicle) : ViewModel() {
    val bookedDates = mutableListOf<CarBookedFullDay>()
    init {
        // Add all timestamps from DB to the calendar
        if (vehicle.bookingStart.size > 0){
            for (i in 0 until vehicle.bookingStart.size){
                bookedDates.add(CarBookedFullDay(LocalDate.of(
                    getYear(vehicle.bookingStart[i]),
                    getMonthNum(vehicle.bookingStart[i]),
                    getDay(vehicle.bookingStart[i])
                ), "Full")
                )
            }
        }
    }
    var chosenDate = mutableStateOf(LocalDate.now().toString())

    private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())
    val vehicleFlow = MutableStateFlow(bookedDates)
    /*val selectedRecipesPriceFlow = vehicleFlow.combine(selectionFlow) { recipes, selection ->
        recipes.filter { it.date in selection }.sumOf { it.text }
    }*/


    fun onSelectionChanged(selection: List<LocalDate>){
        selectionFlow.value = selection
        chosenDate.value = selectionFlow.value.toString()
    }
}
