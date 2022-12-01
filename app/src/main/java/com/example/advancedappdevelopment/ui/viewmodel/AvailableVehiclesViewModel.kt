package com.example.advancedappdevelopment.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.firebaseAdapter.vehicles
import com.example.advancedappdevelopment.ui.view.reusables.AvailableVehicleCard

class AvailableVehiclesViewModel: ViewModel() {

	@Composable
	fun GetVehicleOverviewView(
		navController: NavController,
		showAssociation: String
	){
		if (vehicles.size > 0) {
			for (i in 0 until vehicles.size) {
				if (vehicles[i].association == showAssociation) {
					AvailableVehicleCard(
						vehicle = vehicles[i],
						navController = navController,
					)
				}
			}
		}
	}
}