package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancedappdevelopment.R
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.example.advancedappdevelopment.data.model.dataClass.User
import com.example.advancedappdevelopment.ui.viewmodel.ProfileViewModel


//fun ProfilerPage(navController: NavController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

@Composable
fun ProfilePage(navController: NavController) {
    val viewModel = ProfileViewModel()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(140.dp)
                    .fillMaxWidth()
            )
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center
                )
                IconButton(modifier = Modifier
                    .padding(start = 20.dp)
                    .size(40.dp),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.DarkGray,
                        contentDescription = "return arrow",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.navigate(NavigationRoute.Homepage.route) }


                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .align(BottomCenter)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .drawBehind {
                        val strokeWidth = density
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            Color.LightGray,
                            Offset(0f, 0f),
                            Offset(size.width, 0f),
                            strokeWidth
                        )
                        drawLine(
                            Color.LightGray,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    })

            {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .align(Alignment.CenterStart),
                        imageVector = Icons.Filled.Face,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .align(Center),
                        text = "Firstname Lastname"
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .drawBehind {
                        val strokeWidth = density
                        val y = size.height - strokeWidth / 2

                        drawLine(
                            Color.LightGray,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .align(Alignment.CenterStart),
                        imageVector = Icons.Filled.Email,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .align(Center),
                        text = "abcd@1234.dk"
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .height(100.dp)
                    .fillMaxWidth()
            )
            {

                Text(modifier = Modifier.align(TopCenter), text = "Service",fontSize = 20.sp, fontWeight = Bold)
                val openDialog = remember { mutableStateOf(false) }

                TextButton(

                    // on below line we are adding modifier.
                    // and padding to it,
                    modifier = Modifier
                        .align(Center)
                        .width(120.dp),
                    // on below line we are adding
                    // on click to our button
                    onClick = {

                        // on below line we are updating
                        // boolean value of open dialog.
                        openDialog.value = !openDialog.value

                        // on below line we are checking if dialog is close
                        if (!openDialog.value) {

                            // on below line we are updating value

                        }
                    }
                ) {

                    // on the below line we are creating a text for our button.
                    Text(text = "FAQ", fontSize = 15.sp, color = colorResource(id = R.color.primary))
                }
                TextButton(

                    // on below line we are adding modifier.
                    // and padding to it,
                    modifier = Modifier
                        .align(Center)
                        .padding(top=55.dp)
                        .width(120.dp)
                        .height(35.dp),
                    // on below line we are adding
                    // on click to our button
                    onClick = {

                        // on below line we are updating
                        // boolean value of open dialog.
                        openDialog.value = !openDialog.value

                        // on below line we are checking if dialog is close
                        if (!openDialog.value) {

                            // on below line we are updating value

                        }
                    }
                ) {

                    // on the below line we are creating a text for our button.
                    Text(text = "Contact Us", fontSize = 15.sp, color = colorResource(id = R.color.primary))
                }


            }
        }
            var active by remember {
                mutableStateOf(false)
            }
            var sizeState by remember { mutableStateOf(0.dp) }
            val size by animateDpAsState(
                targetValue = sizeState,
                tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )
            Box(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .fillMaxWidth()
            )
            {
                Button(
                    onClick = {
                        if (active)
                            sizeState = 0.dp
                        else
                            sizeState = 400.dp

                        active = !active
                    },
                    modifier = Modifier
                        .align(Center)
                        .width(300.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary))
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Black,
                        modifier = Modifier.padding(7.dp)
                    )
                }
            }

        }
    }