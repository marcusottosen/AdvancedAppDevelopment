package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.ui.viewmodel.AvailableVehiclesViewModel


@Composable
fun AvailableVehiclesPage(navController: NavController){
    val viewModel = AvailableVehiclesViewModel()

    LazyColumn( //TODO For each available vehicle in association, show card
        modifier = Modifier
            .fillMaxSize()
    ){
        item {
            PageTop()   //The drawer design at the top of the page
            Text(
                text = "Available vehicles",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            )
            viewModel.GetOverviewView()
        }
        item {
            Text(text = "HOME")
            Button(onClick = {
                navController.navigate(NavigationRoute.CarInfo.route) {
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
}

@Composable
fun PageTop(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.BottomCenter)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .background(colorResource(R.color.dark_gray))
            ){
                LazyRow(    //TODO For each assigned association, show button
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center)
                {
                    item {
                        Button(
                            onClick = { /*TODO*/ },
                            Modifier
                                .padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.primary))
                        ) {
                            Text(
                                text = "Høje haver",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    item {
                        Button(
                            onClick = { /*TODO*/ },
                            Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.inactive))
                        ) {
                            Text(
                                text = "Taastrupp sportsklub",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 135.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp, 27.dp))
                    .background(colorResource(R.color.background))
            )
        }
    }
}