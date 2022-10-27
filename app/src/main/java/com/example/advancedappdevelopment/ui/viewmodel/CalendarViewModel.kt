package com.example.advancedappdevelopment.ui.viewmodel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate

data class CarBookedFullDay(
    val date: LocalDate,
    val price: Double,
)

// https://github.com/boguszpawlowski/ComposeCalendar
class CalendarViewModel : ViewModel() {
    var chosenDate = mutableStateOf("eh")

    private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())
    val vehicleFlow = MutableStateFlow(
        listOf(
            CarBookedFullDay(LocalDate.now().plusDays(1), 20.0),
            CarBookedFullDay(LocalDate.now().plusDays(3), 20.0),
            CarBookedFullDay(LocalDate.now().plusDays(5), 10.0),
            CarBookedFullDay(LocalDate.now().plusDays(-2), 25.0),
        )
    )
    val selectedRecipesPriceFlow = vehicleFlow.combine(selectionFlow) { recipes, selection ->
        recipes.filter { it.date in selection }.sumOf { it.price }
    }

    fun onSelectionChanged(selection: List<LocalDate>){
        selectionFlow.value = selection
        chosenDate.value = selectionFlow.value.toString()
    }


}
