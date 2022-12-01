package com.example.advancedappdevelopment.ui.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser

class ProfileViewModel : ViewModel() {
    var currentUser = CurrentUser

//TODO: Burde ikke være composeables her
@Composable
fun GetProfileView() {
    Column(
        modifier = Modifier.padding(start = 45.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = currentUser.name)
        Text(text = currentUser.email)
    }
}}