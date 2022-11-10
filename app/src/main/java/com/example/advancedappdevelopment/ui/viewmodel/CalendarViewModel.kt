package com.example.advancedappdevelopment.ui.viewmodel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.bkskjold.data.util.getDay
import com.example.bkskjold.data.util.getHour
import com.example.bkskjold.data.util.getMonthNum
import com.example.bkskjold.data.util.getYear
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

data class CarBookedDay(
    val date: LocalDate,
    val text: String,
)

/**
 * Calendar item from https://github.com/boguszpawlowski/ComposeCalendar
 * But with custom implementation
 */
class CalendarViewModel(vehicle: Vehicle) : ViewModel() {
    val bookedDates = mutableListOf<CarBookedDay>()
    val bookedHoursPerDay = mutableListOf<MutableList<Any>>()

    init {
        // Add all timestamps from DB to the calendar
        if (vehicle.bookingStart.size > 0){
            for (i in 0 until vehicle.bookingStart.size){
                bookedDates.add(CarBookedDay(LocalDate.of(
                    getYear(vehicle.bookingStart[i]),
                    getMonthNum(vehicle.bookingStart[i]),
                    getDay(vehicle.bookingStart[i])
                ), "Full")
                )
            }
        }

        // Create list of booked hours per day
        for (i in 0 until vehicle.bookingStart.size){
            val day = mutableListOf<Any>()
            day.add(LocalDate.of(
                getYear(vehicle.bookingStart[i]),
                getMonthNum(vehicle.bookingStart[i]),
                getDay(vehicle.bookingStart[i])).toString())

            for (j in getHour(vehicle.bookingStart[i]) .. getHour(vehicle.bookingEnd[i])){
                day.add(j)
            }
            bookedHoursPerDay.add(day)  // Adds list of hours to that day
        }
        // for each list in the list, print its element in matrix form
        /*
        println("MATRIX")
        for(hourList in bookedHoursPerDay) {
            for (j in hourList){
                print("$j, ")
                //print(bookedHoursPerDay[0][0])
            }
            println() // new line after each row
        }
        */
    }
    private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())

    val vehicleFlow = MutableStateFlow(bookedDates)
    /*val selectedRecipesPriceFlow = vehicleFlow.combine(selectionFlow) { recipes, selection ->
        recipes.filter { it.date in selection }.sumOf { it.text }
    }*/

    var chosenDate = mutableStateOf(LocalDate.now().toString())

    fun onSelectionChanged(selection: List<LocalDate>){
        selectionFlow.value = selection
        chosenDate.value = selectionFlow.value.toString()
        chosenDate.value = chosenDate.value.replace("[", "")    // By default [] surrounds the string
        chosenDate.value = chosenDate.value.replace("]", "")
        println(chosenDate.value)
    }

    val hourList = listOf("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
        "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
        "20:00", "21:00", "22:00", "23:00")

}