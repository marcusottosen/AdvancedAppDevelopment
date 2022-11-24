package com.example.advancedappdevelopment.ui.view.reusables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancedappdevelopment.R

@Composable
fun BuyAnimation(){
	println("BUYANIMATION")
	var sizeState by remember { mutableStateOf(0.dp) }
	sizeState = 600.dp
	val size by animateDpAsState(
		targetValue = sizeState,
		tween(
			durationMillis = 1000,
			easing = FastOutSlowInEasing
		)
	)

	Box(
		Modifier
			.height(size)
			.fillMaxWidth()
			.background(colorResource(id = R.color.dark_gray))) {
		Text(text = "helloooooo")
	}
}

