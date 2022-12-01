package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute

@Composable
fun CheckoutSuccess(navController: NavController){
	Column(
		Modifier
			.fillMaxSize()
			.background(colorResource(id = R.color.primary)),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			modifier = Modifier
				.size(100.dp)
				.clip(RoundedCornerShape(50.dp))
				.background(colorResource(id = R.color.background))
		) {
			Icon(Icons.Rounded.Check,
				tint = colorResource(id = R.color.primary),
				modifier = Modifier
					.align(Alignment.Center)
					.size(60.dp),
				contentDescription = "Localized description")
		}

		Spacer(modifier = Modifier.padding(top = 20.dp))

		Text(
			text = "Booking placed!",
			color = colorResource(id = R.color.background),
			fontSize = 28.sp,
			fontWeight = FontWeight.Bold
		)

		Spacer(modifier = Modifier.padding(top = 20.dp))

		Text(
			text = "Your booking was placed successfully. \n " +
					"When your booking starts you can get the keys from the locked box in the parking lot",
			color = colorResource(id = R.color.background),
			textAlign = TextAlign.Center,
			fontSize = 15.sp,
			fontWeight = FontWeight.Light
		)

		Spacer(modifier = Modifier.padding(top = 40.dp))

		Button(onClick = { navController.navigate(NavigationRoute.Homepage.route) },
			shape = RoundedCornerShape(12.dp),
			colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background))
		) {
			Text(
				text = "Back to homepage",
				color = colorResource(id = R.color.primary),
				fontWeight = FontWeight.SemiBold
			)
		}
	}
}