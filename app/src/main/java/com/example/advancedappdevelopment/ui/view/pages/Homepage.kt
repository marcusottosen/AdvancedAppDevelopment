package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.R


@Composable
fun Homepage(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        PageTop()   //The drawer design at the top of the page

        Text(text = "HOME")
        Button(onClick = {
            navController.navigate(NavigationRoute.CarInfo.route){
                launchSingleTop = true
            }
        }) {
            Text(text = "Carinfo")
        }
        Button(onClick = {
            navController.navigate(NavigationRoute.Checkout.route)
        }) {
            Text(text = "checkout")
        }
    }
}

@Composable
fun PageTop(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .height(135.dp)
                    .fillMaxWidth()
                    .background(colorResource(R.color.dark_gray))
            ){
                Row(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { /*TODO*/ }, Modifier.padding(20.dp)) {
                        Text(text = "One")
                    }
                    Button(onClick = { /*TODO*/ }, Modifier.padding(20.dp)) {
                        Text(text = "Two")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 110.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp))
                    .background(colorResource(R.color.background))
            )
        }
    }
}