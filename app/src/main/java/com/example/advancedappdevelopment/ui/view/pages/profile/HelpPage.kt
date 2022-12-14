package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.helps
import com.example.advancedappdevelopment.ui.view.reusables.DropDownMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HelpPage (navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
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
                            text = "Help",
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
                }
            }
            items(helps.size) { help ->
                Box(modifier = Modifier.padding(horizontal = 35.dp)
                ) {
                    DropDownMenu(
                        question = helps[help].question,
                        answer = helps[help].answer,
                        number = helps[help].number)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            item {Spacer(modifier=Modifier.height(100.dp))}
        }
    }
}