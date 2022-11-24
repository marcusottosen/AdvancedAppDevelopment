package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancedappdevelopment.R
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser.id
import com.example.advancedappdevelopment.ui.viewmodel.RegisterViewModel
import com.google.firebase.firestore.auth.User


//fun ProfilerPage(navController: NavController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

@Composable
fun ProfilePage(navController: NavController, currentUser: CurrentUser) {

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
            ) {

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