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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.example.advancedappdevelopment.data.model.firebaseAdapter.updateCurrentUser
import com.example.advancedappdevelopment.ui.view.reusables.ProfileButtons
import com.example.advancedappdevelopment.ui.viewmodel.ProfileViewModel
import com.example.advancedappdevelopment.ui.viewmodel.RegisterViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePage(navController: NavController, viewModel: ProfileViewModel = viewModel()) {

    val currentUser = viewModel.currentUser
    val mail = viewModel.currentUser.email

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
                    .height(100.dp)
                    .fillMaxWidth())
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
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
                        .size(65.dp)
                        .align(BottomCenter)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .drawBehind {
                        val strokeWidth = density
                        val end = size.height - strokeWidth / 2
                        drawLine(
                            Color.LightGray,
                            Offset(150f, 0f),
                            Offset(size.width, 0f),
                            strokeWidth
                        )
                        drawLine(
                            Color.LightGray,
                            Offset(150f, end),
                            Offset(size.width, end),
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
                            .padding(start = 15.dp)
                            .align(CenterStart),
                        imageVector = Icons.Filled.Face,
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .align(Center),
                        text = currentUser.name
                    )
                    Box(modifier = Modifier.align(CenterEnd)){
                        AlertDialogSample(viewModel(),navController)}
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .drawBehind {
                        val strokeWidth = density
                        val end = size.height - strokeWidth / 2

                        drawLine(
                            Color.LightGray,
                            Offset(150f, end),
                            Offset(size.width, end),
                            strokeWidth
                        )
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(CenterStart),
                    imageVector = Icons.Filled.Email,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .align(Center),
                    text = CurrentUser.email
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(modifier = Modifier
                    .align(CenterStart)
                    .padding(start = 15.dp), text = "About", fontSize = 15.sp,color = Color.Gray)
            }

            ProfileButtons(navController = navController, image = Icons.Filled.Info, description = "How it works",pages = 0)
            ProfileButtons(navController = navController,  image = Icons.Filled.Warning, description = "Help", pages = 1)
            ProfileButtons(navController = navController, image = Icons.Filled.Phone, description = "Contact us", pages = 2)

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
                    navController.navigate(NavigationRoute.LoginRegisterPage.route)
                    Firebase.auth.signOut()
                },
                modifier = Modifier
                    .align(Center)
                    .width(300.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary))
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = ""
                )
                Text(
                    text = "Logout",
                    color = Color.Black,
                    modifier = Modifier.padding(7.dp)
                )
            }
        }
    }
}

@Composable
fun AlertDialogSample( viewModel: RegisterViewModel = viewModel(), navController: NavController) {
    MaterialTheme {
        Column {
            val NameEditAlert = remember { mutableStateOf(false)  }
            val name: String by viewModel.name.observeAsState("")

            Text(modifier = Modifier
                .clickable { NameEditAlert.value = true }
                .padding(end = 10.dp)
                ,text = "Edit name"
                , fontSize = 13.sp
                , fontWeight = FontWeight.SemiBold
                ,color =colorResource(id = R.color.primary))

            if (NameEditAlert.value) {
                AlertDialog(
                    onDismissRequest = {
                        NameEditAlert.value = false
                    },
                    title = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(modifier = Modifier.align(Center),
                                text = "Enter your name of choice",
                                fontWeight = FontWeight.SemiBold)
                        }
                    },

                    text = {
                        OutlinedTextField(modifier = Modifier
                            .width(300.dp)
                            .height(50.dp)
                            .background(Color.White, RoundedCornerShape(50)),
                            value = name,
                            onValueChange = { viewModel.updateName(it) },
                        )
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                viewModel.editUsername()
                                updateCurrentUser()
                                NameEditAlert.value = false
                                navController.navigate(NavigationRoute.ProfilePage.route)
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary))) {
                            Text(text = "Confirm Name",
                                color = Color.Black)
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                NameEditAlert.value = false
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary))) {
                            Text(text = "Dismiss",
                                color = Color.Black)
                        }
                    }
                )
            }
        }
    }
}