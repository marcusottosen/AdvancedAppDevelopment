package com.example.advancedappdevelopment.ui.view.reusables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute

@Composable
fun ProfileButtons(
    navController: NavController,
    image: ImageVector,
    description: String,
    pages : Int

    ) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = {

            if (pages == 0) {
                navController.navigate(NavigationRoute.HiwPage.route)
            } else if (pages == 1) {
                navController.navigate(NavigationRoute.HelpPage.route)
            } else if (pages == 2) {
                navController.navigate(NavigationRoute.ContactPage.route)
            }
        },
    ) {
        Box(modifier = Modifier.align(CenterVertically)) {

            Icon(
                imageVector = image,
                tint = Color.Black,
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
        ) {
            Text(
                modifier = Modifier
                    .width(100.dp)
                    .padding(start = 10.dp),
                text = description,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black

            )

            Icon(
                modifier = Modifier.align(CenterEnd),
                imageVector = Icons.Filled.ArrowForward,
                tint = Color.Gray,
                contentDescription = null,

                )
        }
    }
}
