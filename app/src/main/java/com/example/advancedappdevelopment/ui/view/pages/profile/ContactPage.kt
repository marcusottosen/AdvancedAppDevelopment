package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute

@Composable
fun ContactPage (navController: NavController) {
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
                    .height(70.dp)
                    .fillMaxWidth()
            )
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Contact us",
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
                            .clickable { navController.navigate(NavigationRoute.ProfilePage.route) }
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
            ){
                Text(text = "If you have any questions regarding our application, you can try to check either 'How it works' or 'Help' in the profile page, where the most common questions should be answered. If you're still looking for an answer, feel free to write or call us.",
                    lineHeight = 20.sp)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(top = 10.dp)) {
                Icon( modifier = Modifier
                    .padding(start = 15.dp)
                    .clickable { }
                    .align(CenterVertically),
                    imageVector = Icons.Filled.Email,
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary))

                Text(modifier = Modifier
                    .clickable { }
                    .padding(start = 8.dp)
                    .align(CenterVertically)
                    ,text = "advancedapp@support.dk"
                    ,color =colorResource(id = R.color.primary))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)) {
                Icon( modifier = Modifier
                    .padding(start = 15.dp)
                    .clickable { }
                    .align(CenterVertically),
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary))

                Text(modifier = Modifier
                    .clickable { }
                    .padding(start = 8.dp)
                    .align(CenterVertically)
                    ,text = "+45 00 00 00 00"
                    ,color =colorResource(id = R.color.primary))
            }
            Text(modifier = Modifier.padding(start = 15.dp),
                text = "Support from 8:00 to 16:00 monday - friday",
                fontWeight = FontWeight.SemiBold)
        }
    }
}
