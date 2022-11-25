package com.example.advancedappdevelopment.ui.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser.name
import com.example.advancedappdevelopment.data.model.dataClass.User
import com.example.bkskjold.data.util.getDayMonth
import com.example.bkskjold.data.util.getYear

class ProfileViewModel {
    val user = CurrentUser
@Composable
fun GetProfileView() {
    Column(
        modifier = Modifier.padding(start = 45.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = user.name)
        Text(text = user.email)
    }
}}