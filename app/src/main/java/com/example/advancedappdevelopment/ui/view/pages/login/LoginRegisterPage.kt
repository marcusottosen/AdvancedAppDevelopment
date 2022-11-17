package com.example.advancedappdevelopment.ui.view.pages.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser

@Composable
fun LoginRegisterPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Velkommen til APP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        //Spacer
        Spacer(modifier = Modifier.height(50.dp))

        // Login button
        Button(
            onClick = { navController.navigate(NavigationRoute.LoginPage.route) },
            modifier = Modifier
            .width(340.dp)
            .height(60.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(50)),
            shape = RoundedCornerShape(50),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            )
        ) {
            Text(
                text = "Log ind",
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        //Spacer
        Spacer(modifier = Modifier.height(20.dp))

        // Register button
        Button(
            onClick = { navController.navigate(NavigationRoute.RegisterPage.route) },
            modifier = Modifier
                .width(340.dp)
                .height(60.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(50)),
            shape = RoundedCornerShape(50),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            Text(
                text = "Tilmeld",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }

}