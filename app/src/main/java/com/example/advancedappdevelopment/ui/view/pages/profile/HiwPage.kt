package com.example.advancedappdevelopment.ui.view.pages.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.hiws


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HiwPage (navController: NavController) {
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
                            text = "How it works",
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

            items(hiws.size) { hiw ->
                Box(modifier = Modifier.padding(horizontal = 35.dp)
                    ) {
                    DropDownMenu(
                        question = hiws[hiw].question,
                        answer = hiws[hiw].answer,
                        number = hiws[hiw].number)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            item {Spacer(modifier=Modifier.height(100.dp))}
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DropDownMenu(
    question: String,
    answer: String,
    number: Int) {
   // var openCard: Int
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState}

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(10.dp)
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = question,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {

                        expandedState = !expandedState
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {

                Spacer(modifier = Modifier.height(5.dp))
                Text(modifier = Modifier.padding(bottom = 10.dp),
                    text = answer,
                    lineHeight = 20.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

