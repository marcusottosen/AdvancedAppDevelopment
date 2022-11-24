package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
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
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser.id
import com.example.advancedappdevelopment.ui.viewmodel.RegisterViewModel
import com.google.firebase.firestore.auth.User


//fun ProfilerPage(navController: NavController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

@Composable
fun ProfilePage(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(modifier = Modifier
            //.background(color = Color.Blue)
            .padding(top = 20.dp)
            .height(140.dp)
            .fillMaxWidth())
        {
            Text( modifier = Modifier.fillMaxWidth(),
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                textAlign = TextAlign.Center)
            IconButton(modifier = Modifier
                .padding(start = 20.dp)
                .size(40.dp),
                onClick = { //navController.navigateUp()
                }
            )  {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.DarkGray,
                    contentDescription = "return arrow",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.navigate(NavigationRoute.Homepage.route)}



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

       
    
}
        }